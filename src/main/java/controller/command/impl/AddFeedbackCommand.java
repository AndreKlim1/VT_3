package controller.command.impl;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Movie;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.FeedbackService;

import java.util.Optional;

public class AddFeedbackCommand implements Command {

    private static final String PAGE = "command=movieInfo";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String RATING = "rating";
    private static final String CONTENT = "content";
    private static final String MOVIE_ID = "movieId";
    private static final String USER_ID = "userId";
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
                int userId = Integer.parseInt(requestContext.getRequestParameter(USER_ID));
                FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
                boolean result = feedbackService.addNewFeedback(stringRating.get(), content.get(), userId, movieId);
                if (result) {
                    message = OK;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT)//UPDATING SCORE FOR ALL USERS!!!
    }
}
