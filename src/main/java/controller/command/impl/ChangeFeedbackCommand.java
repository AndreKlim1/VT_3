package controller.command.impl;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;
import service.api.UserService;

import java.util.List;
import java.util.Optional;

public class ChangeFeedbackCommand{

    private static final String PAGE = "redirect:goMovieInfo";
    private static final String ERROR_PAGE = "error";
    private static final String RATING = "rating";
    private static final String CONTENT = "content";
    private static final String MOVIE_ID = "movieId";
    private static final String USER = "user";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    @RequestMapping(value = "/changeFeedback", method = RequestMethod.POST)
    public String execute(@RequestParam(RATING) Optional<String> stringRating, @RequestParam(CONTENT) Optional<String> content, @RequestParam(MOVIE_ID) int movieId, Model model, HttpSession session) {
        String message = ERROR;

        try {
            if (stringRating.isPresent() && content.isPresent()) {

                User user = (User) session.getAttribute(USER);
                int userId = user.getId();
                FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
                Optional<Feedback> feedback = feedbackService.retrieveFeedbackByUserAndMovieId(userId, movieId);
                boolean result = feedbackService.updateFeedbackInformation(feedback.get().getId(), stringRating.get(), content.get());

                MovieService movieService = ServiceFactory.getInstance().getMovieService();
                movieService.updateMovieAverageRatingById(movieId);

                if (result) {
                    message = OK;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return ERROR_PAGE;
        }
        return PAGE + MESSAGE_PARAMETER + message + "&" +MOVIE_ID +"=" + Integer.toString(movieId);
    }


}
