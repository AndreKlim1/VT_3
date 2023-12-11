package controller.command.impl.transition;

import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GoToLogInCommand {

    private static final String PAGE = "login";

    @RequestMapping(value = "/goLogin", method = RequestMethod.GET)
    public String execute(Model model) {

        return PAGE;
    }
}
