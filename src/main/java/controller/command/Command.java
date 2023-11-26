package controller.command;

import controller.context.RequestContextHelper;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(RequestContextHelper helper, HttpServletResponse response);
}
