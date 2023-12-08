package controller.command.impl.transition;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Movie;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.MovieService;

import java.util.Optional;

public class GoToChangeMovieCommand implements Command {
    private static final String MOVIE_ID= "movieId";
    private static final String MOVIE= "movie";
    private static final String PAGE = "WEB-INF/view/changeMovie.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        try{
        int movieId = Integer.parseInt(requestContext.getRequestParameter(MOVIE_ID));
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        Optional<Movie> movie = movieService.retrieveMovieById(movieId);
        movie.ifPresent(information -> requestContext.addRequestAttribute(MOVIE, information));
        } catch (
        ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);}
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
