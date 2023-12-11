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
import service.api.UserService;

@Controller
public class BanUserCommand {

    private static final String PAGE = "redirect:goMovieInfo";
    private static final String USER_ID = "userId";
    private static final String MOVIE_ID = "movieId";
    private static final String ERROR_PAGE = "error";

    @RequestMapping(value = "/banUser", method = RequestMethod.GET)
    public String execute(@RequestParam(USER_ID) int userId, @RequestParam(MOVIE_ID) int movieId,  Model model) {
        try{
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.banUsersById(userId, true);
        } catch (ServiceException e) {
            return ERROR_PAGE;
        }
        return PAGE+ "&movieId=" + movieId;
    }


}
