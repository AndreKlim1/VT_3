package controller.command.impl;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.User;
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
public class ChangeUserStatusCommand  {

    private static final String PAGE = "movieInfo";
    private static final String ERROR_PAGE = "error";
    private static final String STATUS_ID= "statusId";
    private static final String USER_ID= "userId";
    private static final String MOVIE_ID= "movieId";

    @RequestMapping(value = "/changeUserStatus", method = RequestMethod.GET)
    public String execute(@RequestParam(STATUS_ID) int statusId, @RequestParam(USER_ID) int userId, @RequestParam(MOVIE_ID) int movieId, Model model) {

        try{

            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.updateUserStatusById(userId, statusId);

        } catch (ServiceException e) {
            return ERROR_PAGE;
        }

        return PAGE + "&movieId=" + movieId;
    }

}
