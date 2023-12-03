package controller.command.impl;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;
import service.api.UserService;

import java.util.List;
import java.util.Optional;

public class ChangeFeedbackCommand implements Command {

    private static final String PAGE = "command="+ CommandName.GO_MOVIE_INFO_COMMAND;
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String RATING = "rating";
    private static final String CONTENT = "content";
    private static final String MOVIE_ID = "movieId";
    private static final String USER = "user";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";
    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {

        RequestContext requestContext = helper.createContext();
        String message = ERROR;

        Optional<String> stringRating = Optional.ofNullable(requestContext.getRequestParameter(RATING));
        Optional<String> content = Optional.ofNullable(requestContext.getRequestParameter(CONTENT));

        try {
            if (stringRating.isPresent() && content.isPresent()) {
                int movieId = Integer.parseInt(requestContext.getRequestParameter(MOVIE_ID));
                User user = (User) requestContext.getSessionAttribute(USER);
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
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
    }
}
