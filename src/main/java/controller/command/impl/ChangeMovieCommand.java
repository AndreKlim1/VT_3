package controller.command.impl;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Movie;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ServiceFactory;
import service.api.MovieService;

import java.util.Optional;
@Controller
public class ChangeMovieCommand {
    private static final String PAGE = "redirect:goMain";
    private static final String ERROR_PAGE = "error";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String MOVIE_ID = "movieId";

    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    @RequestMapping(value = "/changeMovie", method = RequestMethod.POST)
    public String execute(@RequestParam(NAME) Optional<String> name, @RequestParam(DESCRIPTION) Optional<String> description, @RequestParam(IMAGE) Optional<String> image, @RequestParam(MOVIE_ID) int movieId, Model model) {
        String message = ERROR;

        try {
            if (name.isPresent() && image.isPresent() && description.isPresent()) {
                MovieService movieService = ServiceFactory.getInstance().getMovieService();
                Optional<Movie> movie = movieService.retrieveMovieById(movieId);
                boolean result = movieService.updateMovieInformation(movie.get().getId(), name.get(), description.get(), image.get(), movie.get().getAverageRating(), movie.get().getFeedbackAmount(), movie.get().getRatedEnough());
                if (result) {
                    message = OK;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return ERROR_PAGE;
        }
        return PAGE + MESSAGE_PARAMETER + message;
    }

}
