package controller.command.impl;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.UserService;

public class BanUserCommand implements Command {

    private static final String PAGE = "command=main";
    private static final String USER_ID = "userId";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        try{
            int userId = Integer.parseInt(requestContext.getRequestParameter(USER_ID));
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.banUsersById(userId, true);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }
}
