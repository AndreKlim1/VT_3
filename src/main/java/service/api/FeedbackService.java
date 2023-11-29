package service.api;

import entity.Feedback;
import exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    List<Feedback> retrieveFeedbackByUserId(int userId) throws ServiceException;

    List<Feedback> retrieveFeedbackByMovieId(int movieId) throws ServiceException;

    Optional<Feedback> retrieveFeedbackById(int id) throws ServiceException;

    Optional<Feedback> retrieveFeedbackByUserAndMovieId(int userId, int movieId) throws ServiceException;

    boolean updateFeedbackInformation(int id, String stringRating, String content) throws ServiceException;

    void removeFeedbackById(int id) throws ServiceException;

    boolean addNewFeedback(String stringRating, String content, int userId, int movieId) throws ServiceException;
}
