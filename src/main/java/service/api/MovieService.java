package service.api;

import entity.Feedback;
import entity.Movie;
import exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean updateMovieInformation(int id, String name, String description, String image, double averageRating, int FeedbackAmount, boolean ratedEnough) throws ServiceException;

    void removeMovieById(int id) throws ServiceException;

    boolean addNewMovie(String name, String description, String image, double averageRating, int FeedbackAmount, boolean ratedEnough) throws ServiceException;

    Optional<Movie> retrieveMovieById(int id) throws ServiceException;

    Optional<Movie> retrieveMovieByName(String name) throws ServiceException;

    List<Movie> retrieveMoviesByAverageRating(double from, double to) throws ServiceException;

    List<Movie> retrieveMoviesByFeedbackAmount(int from, int to) throws ServiceException;

    boolean updateMovieAverageRatingById(int id) throws ServiceException;

    boolean updateMovieFeedbackAmountById(int id) throws ServiceException;

    void checkFeedbacksByMovieId(int id) throws ServiceException;
}
