package controller.command.impl;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.MovieService;

public class DeleteMovieCommand implements Command {
    private static final String PAGE = "command="+ CommandName.GO_MAIN_COMMAND;
    private static final String MOVIE_ID = "movieId";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try {
            int movieId = Integer.parseInt(requestContext.getRequestParameter(MOVIE_ID));
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            movieService.removeMovieById(movieId);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }
}
