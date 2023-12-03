package controller.command.impl;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;

import java.util.Optional;

public class AddMovieCommand implements Command {

    private static final String PAGE = "command="+ CommandName.GO_MAIN_COMMAND;
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";

    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";
    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {

        RequestContext requestContext = helper.createContext();
        String message = ERROR;

        Optional<String> name = Optional.ofNullable(requestContext.getRequestParameter(NAME));
        Optional<String> description = Optional.ofNullable(requestContext.getRequestParameter(DESCRIPTION));
        Optional<String> image = Optional.ofNullable(requestContext.getRequestParameter(IMAGE));

        try {
            if (name.isPresent() && image.isPresent() && description.isPresent()) {
                MovieService movieService = ServiceFactory.getInstance().getMovieService();
                boolean result = movieService.addNewMovie(name.get(), description.get(), image.get(), 0, 0, false);
                if (result) {
                    message = OK;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
    }
}
