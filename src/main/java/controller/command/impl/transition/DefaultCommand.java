package controller.command.impl.transition;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DefaultCommand {
    private static final String PAGE = "error";

    @RequestMapping(value = "/goError", method = RequestMethod.GET)
    public String execute(ModelMap modelMap) {

        return PAGE;
    }
}
