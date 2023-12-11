package controller.command.impl;


import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ServiceFactory;
import service.api.UserService;

import java.util.Optional;

@Controller
public class LogUpCommand{
    private static final String PAGE = "logup";
    private static final String ERROR_PAGE = "error";
    private static final String EMAIL = "email";
    private static final String PASSWORD_FIRST = "password-first";
    private static final String PASSWORD_SECOND = "password-second";
    private static final String NICKNAME = "nickname";
    private static final String MESSAGE = "message";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    @RequestMapping(value = "/logup", method = RequestMethod.GET)
    public String execute(@RequestParam(EMAIL) Optional<String> email, @RequestParam(PASSWORD_FIRST) Optional<String> passwordFirst, @RequestParam(PASSWORD_SECOND) Optional<String> passwordSecond, @RequestParam(NICKNAME) Optional<String> nickname, Model model) {

        String message = ERROR;

        try {
            if (email.isPresent() && passwordFirst.isPresent() && passwordSecond.isPresent() &&
                    nickname.isPresent()) {
                if (passwordFirst.get().equals(passwordSecond.get())) {
                    UserService userService = ServiceFactory.getInstance().getUserService();
                    boolean result = userService.register(nickname.get(), email.get(), DigestUtils.sha1Hex(passwordFirst.get()));
                    if (result) message = OK;
                }
            }
        } catch (ServiceException e) {
            return ERROR_PAGE;
        }
        model.addAttribute(MESSAGE, message);
        return PAGE;
    }


}
