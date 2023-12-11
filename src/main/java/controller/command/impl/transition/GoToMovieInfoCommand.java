package controller.command.impl.transition;


import entity.Review;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import entity.Movie;
import entity.Status;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;
import service.api.StatusService;
import service.api.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
public class GoToMovieInfoCommand {

    private static final String PAGE = "movieInfo";
    private static final String ERROR_PAGE = "error";
    private static final String MOVIE_ID= "movieId";
    private static final String MOVIE= "movie";
    private static final String REVIEWS = "reviews";

    private static final String FEEDBACK= "feedback";
    private static final String USER= "user";
    private static final String STATUSES= "statuses";

    @RequestMapping(value = "/goMovieInfo", method = RequestMethod.GET)
    public String execute(@RequestParam(MOVIE_ID) int movieId, Model model, HttpSession session) {

        try{
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            Optional<Movie> movie = movieService.retrieveMovieById(movieId);
            movie.ifPresent(information -> model.addAttribute(MOVIE, information));

            FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
            List<Feedback> feedbacks = feedbackService.retrieveFeedbackByMovieId(movieId);
            UserService userService = ServiceFactory.getInstance().getUserService();
            StatusService statusService = ServiceFactory.getInstance().getStatusService();
            List<Review> reviews = new ArrayList<>();
            int id=0;
            for(Feedback feedback : feedbacks){
                id++;
                User user = userService.retrieveUserById(feedback.getUserId()).get();
                reviews.add(new Review(id, feedback, user, statusService.retrieveStatusById(user.getStatusId()).get()));
            }
            model.addAttribute(REVIEWS, reviews);

            User user = (User) session.getAttribute(USER);
            if (user == null) {

                return PAGE;
            }

            Optional<Feedback> feedback = feedbackService.retrieveFeedbackByUserAndMovieId(user.getId(), movieId);
            feedback.ifPresent(information -> model.addAttribute(FEEDBACK, information));

            List<Status> statuses = statusService.retrieveAllStatuses();
            model.addAttribute(STATUSES, statuses);


        } catch (ServiceException e) {
            return ERROR_PAGE;
        }

        return PAGE;
    }
}
