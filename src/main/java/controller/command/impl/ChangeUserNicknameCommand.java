package controller.command.impl;

import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Movie;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ServiceFactory;
import service.api.MovieService;
import service.api.UserService;

import java.util.List;

@Controller
public class ChangeUserNicknameCommand{
    private static final String PAGE = "profile";
    private static final String ERROR_PAGE = "error";
    private static final String NICKNAME= "nickname";
    private static final String USER= "user";

    @RequestMapping(value = "/changeUserNickname", method = RequestMethod.POST)
    public String execute(@RequestParam(NICKNAME) String nickname, Model model, HttpSession session) {
        User user;

        try{
            user = (User) session.getAttribute(USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.updateUserNicknameById(user.getId(), nickname);

        } catch (ServiceException e) {
            return ERROR_PAGE;
        }

        session.setAttribute(USER, user);
        return PAGE;
    }
}
