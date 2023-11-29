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
import service.api.MovieService;
import service.api.UserService;

import java.util.List;

public class ChangeUserNicknameCommand implements Command {
    private static final String PAGE = "WEB-INF/view/profile.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String NICKNAME= "nickname";
    private static final String USER= "user";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try{
            String nickname = requestContext.getRequestParameter(NICKNAME);
            User user = (User) requestContext.getSessionAttribute(USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.updateUserNicknameById(user.getId(), nickname);

        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }


        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
