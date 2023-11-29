package controller.command.impl;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;

public class DeleteFeedbackCommand implements Command {

    private static final String PAGE = "command=main";
    private static final String FEEDBACK_ID = "feedbackId";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            int feedbackId = Integer.parseInt(requestContext.getRequestParameter(FEEDBACK_ID));
            FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
            feedbackService.removeFeedbackById(feedbackId);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }
}
