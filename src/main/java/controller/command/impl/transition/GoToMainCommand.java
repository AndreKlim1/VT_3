package controller.command.impl.transition;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import dao.DaoFactory;
import entity.Movie;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ServiceFactory;
import service.api.MovieService;

import java.util.List;
@Controller
public class GoToMainCommand {

    private static final String PAGE = "main";
    private static final String ERROR_PAGE = "error";
    private static final String MOVIES= "movies";

    @RequestMapping(value = "/goMain", method = RequestMethod.GET)
    public String execute(Model model) {
        try{
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            List<Movie> movies = movieService.retrieveAllMovies();
            model.addAttribute(MOVIES,movies);


        } catch (ServiceException e) {
            return ERROR_PAGE;
        }

        return PAGE;
    }


}
