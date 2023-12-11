package controller.command.impl;

import controller.command.impl.transition.GoToLogInCommand;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class LogOutCommand {

    private static final String PAGE = "redirect:goLogin";
    private static final String USER = "user";
    private static final String ROLE = "role";

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String execute(Model model, HttpSession session) {

        session.removeAttribute(USER);
        session.removeAttribute(ROLE);
        return PAGE;
    }

}
