package controller.command.impl.transition;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Movie;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ServiceFactory;
import service.api.MovieService;

import java.util.Optional;
@Controller
public class GoToChangeMovieCommand {
    private static final String MOVIE_ID= "movieId";
    private static final String MOVIE= "movie";
    private static final String PAGE = "changeMovie";
    private static final String ERROR_PAGE = "error";

    @RequestMapping(value = "/goChangeMovie", method = RequestMethod.GET)
    public String execute(@RequestParam(MOVIE_ID) int movieId, Model model) {
        try{
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            Optional<Movie> movie = movieService.retrieveMovieById(movieId);
            movie.ifPresent(information -> model.addAttribute(MOVIE, information));
        } catch (
                ServiceException e) {return ERROR_PAGE;}
        return PAGE;
    }

}
