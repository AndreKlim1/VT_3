package controller.command.impl;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Role;
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
import service.api.RoleService;
import service.api.UserService;

import java.util.Optional;
@Controller
public class LogInCommand{

    private static final String PROFILE_PAGE = "redirect:goProfile";
    private static final String ERROR_PAGE = "error";
    private static final String LOGIN_PAGE = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String EMAIL_PARAMETER = "email";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String ERROR_MESSAGE = "errorMessage";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String execute(@RequestParam(PASSWORD_PARAMETER) String password, @RequestParam(EMAIL_PARAMETER) String login, Model model, HttpSession session) {

        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            Optional<User> optionalResult = userService.login(login, password);

            if (optionalResult.isPresent()) {
                session.setAttribute(USER, optionalResult.get());

                RoleService roleService = ServiceFactory.getInstance().getRoleService();
                Optional<Role> role = roleService.retrieveRoleById(optionalResult.get().getRoleId());
                role.ifPresent(value -> model.addAttribute(ROLE, value));

                return PROFILE_PAGE;
            }
        } catch (ServiceException e) {
            return ERROR_PAGE;
        }

        model.addAttribute(ERROR_MESSAGE, true);
        return LOGIN_PAGE;
    }
}
