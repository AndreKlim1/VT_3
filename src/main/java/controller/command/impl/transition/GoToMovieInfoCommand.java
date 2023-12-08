package controller.command.impl.transition;

import controller.command.Command;
import controller.command.CommandResult;
import controller.command.CommandResultType;
import entity.Review;
import controller.context.RequestContext;
import controller.context.RequestContextHelper;
import entity.Feedback;
import entity.Movie;
import entity.Status;
import entity.User;
import exceptions.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.api.FeedbackService;
import service.api.MovieService;
import service.api.StatusService;
import service.api.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoToMovieInfoCommand implements Command {

    private static final String PAGE = "WEB-INF/view/movieInfo.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String MOVIE_ID= "movieId";
    private static final String MOVIE= "movie";
    private static final String REVIEWS = "reviews";

    private static final String FEEDBACK= "feedback";
    private static final String USER= "user";
    private static final String STATUS= "status";


    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        try{
            int movieId = Integer.parseInt(requestContext.getRequestParameter(MOVIE_ID));
            MovieService movieService = ServiceFactory.getInstance().getMovieService();
            Optional<Movie> movie = movieService.retrieveMovieById(movieId);
            movie.ifPresent(information -> requestContext.addRequestAttribute(MOVIE, information));

            FeedbackService feedbackService = ServiceFactory.getInstance().getFeedbackService();
            List<Feedback> feedbacks = feedbackService.retrieveFeedbackByMovieId(movieId);
            UserService userService = ServiceFactory.getInstance().getUserService();
            StatusService statusService = ServiceFactory.getInstance().getStatusService();
            List<Review> reviews = new ArrayList<>();
            int id=0;
            for(Feedback feedback : feedbacks){
                id++;
                User user = userService.retrieveUserById(feedback.getUserId()).get();
                reviews.add(new Review(id, feedback, user, statusService.retrieveStatusById(user.getStatusId()).get()));
            }
            requestContext.addRequestAttribute(REVIEWS, reviews);

            User user = (User) requestContext.getSessionAttribute(USER);
            if (user == null) {
                helper.updateRequest(requestContext);
                return new CommandResult(PAGE, CommandResultType.FORWARD);
            }

            Optional<Feedback> feedback = feedbackService.retrieveFeedbackByUserAndMovieId(user.getId(), movieId);
            feedback.ifPresent(information -> requestContext.addRequestAttribute(FEEDBACK, information));

            Optional<Status> status = statusService.retrieveStatusById(user.getStatusId());
            status.ifPresent(information -> requestContext.addRequestAttribute(STATUS, information));

        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }


        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
