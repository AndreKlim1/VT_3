package controller.command.impl;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.UserService;

public class ChangeUserStatusCommand implements Command {

    private static final String PAGE = "WEB-INF/view/movie.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String STATUS_ID= "status_id";
    private static final String USER= "user";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try{
            int status_id = Integer.parseInt(requestContext.getRequestParameter(STATUS_ID));
            User user = (User) requestContext.getSessionAttribute(USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.updateUserStatusById(user.getId(), status_id);

        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }


        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
