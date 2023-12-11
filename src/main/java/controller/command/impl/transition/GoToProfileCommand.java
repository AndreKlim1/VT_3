package controller.command.impl.transition;

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
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;
import service.api.StatusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
public class GoToProfileCommand {

    private static final String PAGE = "profile";
    private static final String ERROR_PAGE = "error";
    private static final String USER = "user";
    private static final String STATUS = "status";
    private static final String MOVIES = "movies";

    @RequestMapping(value = "/goProfile", method = RequestMethod.GET)
    public String execute(Model model, HttpSession session) {

        User user = (User) session.getAttribute(USER);
        if (user == null) {
            return PAGE;
        }

        try {
            int statusId = user.getStatusId();
            StatusService statusService = ServiceFactory.getInstance().getStatusService();

            Optional<Status> status = statusService.retrieveStatusById(statusId);
            status.ifPresent(information -> model.addAttribute(STATUS, information));

            FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
            List<Feedback> feedbacks = feedbackService.retrieveFeedbackByUserId(user.getId());
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            List<Movie> movies = new ArrayList<>();
            for(Feedback feedback : feedbacks){
                Optional<Movie> movie = movieService.retrieveMovieById(feedback.getMovieId());
                movies.add(movie.get());
            }
            model.addAttribute(MOVIES, movies);

        } catch (ServiceException e) {
            return ERROR_PAGE;
        }

        return PAGE;
    }

}
