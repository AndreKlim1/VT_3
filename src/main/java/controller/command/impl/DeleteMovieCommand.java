package controller.command.impl;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ServiceFactory;
import service.api.MovieService;

@Controller
public class DeleteMovieCommand {
    private static final String PAGE = "redirect:goMain";
    private static final String MOVIE_ID = "movieId";
    private static final String ERROR_PAGE = "error";

    @RequestMapping(value = "/deleteMovie", method = RequestMethod.GET)
    public String execute(@RequestParam(MOVIE_ID) int movieId, Model model) {
        try {
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            movieService.removeMovieById(movieId);
        } catch (ServiceException e) {
            return ERROR_PAGE;
        }

        return PAGE;
    }

}
