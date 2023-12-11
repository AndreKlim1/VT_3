package controller.command.impl;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;

@Controller
public class DeleteFeedbackCommand  {

    private static final String PAGE = "redirect:goMovieInfo";
    private static final String FEEDBACK_ID = "feedbackId";
    private static final String ERROR_PAGE = "error";
    private static final String MOVIE_ID = "movieId";

    @RequestMapping(value = "/deleteFeedback", method = RequestMethod.GET)
    public String execute(@RequestParam(MOVIE_ID) int movieId, @RequestParam(FEEDBACK_ID) int feedbackId, Model model) {
        try {
            FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
            feedbackService.removeFeedbackById(feedbackId);

            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            movieService.updateMovieAverageRatingById(movieId);
            movieService.updateMovieFeedbackAmountById(movieId);

        } catch (ServiceException e) {
            return ERROR_PAGE;
        }
        return PAGE;
    }
}
