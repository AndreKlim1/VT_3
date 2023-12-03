package controller.command.impl;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import entity.Movie;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;
import service.api.UserService;

import java.util.List;
import java.util.Optional;

public class AddFeedbackCommand implements Command {

    private static final String PAGE = "command="+ CommandName.GO_MOVIE_INFO_COMMAND;
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String RATING = "rating";
    private static final String CONTENT = "content";
    private static final String MOVIE_ID = "movieId";
    private static final String USER = "user";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";
    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {

        RequestContext requestContext = helper.createContext();
        String message = ERROR;

        Optional<String> stringRating = Optional.ofNullable(requestContext.getRequestParameter(RATING));
        Optional<String> content = Optional.ofNullable(requestContext.getRequestParameter(CONTENT));

        try {
            if (stringRating.isPresent() && content.isPresent()) {
                int movieId = Integer.parseInt(requestContext.getRequestParameter(MOVIE_ID));
                User user = (User) requestContext.getSessionAttribute(USER);
                int userId = user.getId();
                FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
                boolean result = feedbackService.addNewFeedback(stringRating.get(), content.get(), userId, movieId);

                MovieService movieService = ServiceFactory.getInstance().getMovieService();
                movieService.updateMovieAverageRatingById(movieId);
                UserService userService = ServiceFactory.getInstance().getUserService();

                if(movieService.updateMovieFeedbackAmountById(movieId)){
                    List<Feedback> feedbacks = feedbackService.retrieveFeedbackByMovieId(movieId);
                    for (Feedback feedback : feedbacks){
                        int addedScore = movieService.calcUserScoreByMovieId(movieId, feedback.getRating());
                        Optional<User> userToChange = userService.retrieveUserById(feedback.getUserId());
                        if(userToChange.isPresent()){
                            int newScore = userToChange.get().getScore()+addedScore;
                            userService.updateUserScoreById(feedback.getUserId(), newScore);
                        }
                    }
                }
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
