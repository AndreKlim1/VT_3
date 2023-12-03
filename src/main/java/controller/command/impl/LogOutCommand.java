package controller.command.impl;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.command.impl.transition.GoToLogInCommand;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import jakarta.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {

    private static final String LOGIN_PAGE = "command="+ CommandName.GO_LOG_IN_COMMAND;
    private static final String USER = "user";
    private static final String ROLE = "role";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        requestContext.removeSessionAttribute(USER);
        requestContext.removeSessionAttribute(ROLE);
        helper.updateRequest(requestContext);
        return new CommandResult(LOGIN_PAGE, CommandResultType.REDIRECT);
    }
}
