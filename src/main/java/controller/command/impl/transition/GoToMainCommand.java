package controller.command.impl.transition;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import dao.DaoFactory;
import entity.Movie;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.MovieService;

import java.util.List;

public class GoToMainCommand implements Command {

    private static final String PAGE = "WEB-INF/view/main.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String MOVIES= "movies";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try{
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            List<Movie> movies = movieService.retrieveAllMovies();
            requestContext.addRequestAttribute(MOVIES,movies);


        } catch (ServiceException e) {
        return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
    }


        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
