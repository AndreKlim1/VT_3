package controller.command.impl.transition;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import entity.Movie;
import entity.Status;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;
import service.api.StatusService;

import java.util.List;
import java.util.Optional;

public class GoToProfileCommand implements Command {

    private static final String PAGE = "WEB-INF/view/profile.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String USER = "user";
    private static final String STATUS = "status";
    private static final String MOVIES = "movies";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }

        try {
            int statusId = user.getStatusId();
            StatusService statusService = ServiceFactory.getInstance().getStatusService();

            Optional<Status> status = statusService.retrieveStatusById(statusId);
            status.ifPresent(information -> requestContext.addRequestAttribute(STATUS, information));

            FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
            List<Feedback> feedbacks = feedbackService.retrieveFeedbackByUserId(user.getId());
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            List<Movie> movies = null
            for(Feedback feedback : feedbacks){
                movies.add(movieService.retrieveMovieById(feedback.getMovieId()).get());
            }
            requestContext.addRequestAttribute(MOVIES, movies);

        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD)
    }
}
