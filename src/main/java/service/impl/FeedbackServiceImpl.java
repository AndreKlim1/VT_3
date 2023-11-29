package service.impl;

import dao.DaoFactory;
import dao.api.FeedbackDao;
import entity.Feedback;
import exceptions.DaoException;
import exceptions.ServiceException;
import service.api.FeedbackService;

import java.util.List;
import java.util.Optional;

public class FeedbackServiceImpl implements FeedbackService {

    @Override
    public List<Feedback> retrieveFeedbackByUserId(int userId) throws ServiceException {
        try {
            FeedbackDao feedbackDao= DaoFactory.getInstance().getFeedbackDao();
            List<Feedback> result = null;

            result = feedbackDao.findByUserId(userId);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Feedback> retrieveFeedbackByMovieId(int movieId) throws ServiceException {
        try {
            FeedbackDao feedbackDao= DaoFactory.getInstance().getFeedbackDao();
            List<Feedback> result = null;

            result = feedbackDao.findByMovieId(movieId);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Feedback> retrieveFeedbackById(int id) throws ServiceException {
        try {
            FeedbackDao feedbackDao= DaoFactory.getInstance().getFeedbackDao();
            Optional<Feedback> result;

            result = feedbackDao.findById(id);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Feedback> retrieveFeedbackByUserAndMovieId(int userId, int movieId) throws ServiceException {
        try {
            FeedbackDao feedbackDao= DaoFactory.getInstance().getFeedbackDao();
            Optional<Feedback> result;

            result = feedbackDao.findByUserAndMovieId(userId, movieId);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateFeedbackInformation(int id, String stringRating, String content) throws ServiceException {
        int rating = Integer.parseInt(stringRating);
        try{
            FeedbackDao feedbackDao= DaoFactory.getInstance().getFeedbackDao();
            Feedback feedback = feedbackDao.findById(id).get();

            feedback.setRating(rating);
            feedback.setContent(content);
            feedbackDao.updateFeedbackById(id, feedback);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void removeFeedbackById(int id) throws ServiceException {
        try {
            FeedbackDao feedbackDao= DaoFactory.getInstance().getFeedbackDao();
            feedbackDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addNewFeedback(String stringRating, String content, int userId, int movieId) throws ServiceException {
        int rating = Integer.parseInt(stringRating);
        Feedback feedback = new Feedback(0, rating, content, userId, movieId);
        try {
            FeedbackDao feedbackDao= DaoFactory.getInstance().getFeedbackDao();
            feedbackDao.save(feedback);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
