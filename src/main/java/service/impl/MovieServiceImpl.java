package service.impl;

import dao.DaoFactory;
import dao.api.FeedbackDao;
import dao.api.MovieDao;
import dao.api.UserDao;
import entity.Feedback;
import entity.Movie;
import entity.User;
import exceptions.DaoException;
import exceptions.ServiceException;
import service.ServiceFactory;
import service.api.MovieService;
import service.api.UserService;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    private final static int ENOUGH_RATED_AMOUNT = 5;
    private final static int RATING_BORDERS = 2;

    @Override
    public boolean updateMovieInformation(int id, String name, String description, String image, double averageRating, int FeedbackAmount, boolean ratedEnough) throws ServiceException {
        try{
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            Movie movie = movieDao.findById(id).get();

            movie.setName(name);
            movie.setDescription(description);
            movie.setImage(image);
            movieDao.updateMovieById(id, movie);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void removeMovieById(int id) throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            FeedbackDao feedbackDao = DaoFactory.getInstance().getFeedbackDao();
            List<Feedback> feedbacks = feedbackDao.findByMovieId(id);
            for(Feedback feedback : feedbacks){
                feedbackDao.removeById(feedback.getId());
            }
            movieDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addNewMovie(String name, String description, String image, double averageRating, int feedbackAmount, boolean ratedEnough) throws ServiceException {

        Movie movie = new Movie(0, name, description, image, averageRating, feedbackAmount, ratedEnough);
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            movieDao.save(movie);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Movie> retrieveMovieById(int id) throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            return movieDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Movie> retrieveMovieByName(String name) throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            return movieDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> retrieveMoviesByAverageRating(double from, double to) throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            return movieDao.findByAverageRating(from, to);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> retrieveMoviesByFeedbackAmount(int from, int to) throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            return movieDao.findByFeedbackAmount(from, to);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> retrieveAllMovies() throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            return movieDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateMovieAverageRatingById(int id) throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            FeedbackDao feedbackDao = DaoFactory.getInstance().getFeedbackDao();
            List<Feedback> feedbacks = feedbackDao.findByMovieId(id);
            int ratingSum = 0;
            for(Feedback feedback : feedbacks){
                ratingSum+= feedback.getRating();
            }
            double averageRating = (double)ratingSum/(double)movieDao.findById(id).get().getFeedbackAmount();
            movieDao.updateAverageRatingById(id, averageRating);
            return true;

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateMovieFeedbackAmountById(int id) throws ServiceException {
        try {
            MovieDao movieDao= DaoFactory.getInstance().getMovieDao();
            FeedbackDao feedbackDao = DaoFactory.getInstance().getFeedbackDao();
            List<Feedback> feedbacks = feedbackDao.findByMovieId(id);
            int feedbackAmount=0;
            if(feedbacks!=null)
                feedbackAmount = feedbacks.size();
            movieDao.updateFeedbackAmountById(id, feedbackAmount);
            checkFeedbacksByMovieId(id);
            return true;

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void checkFeedbacksByMovieId(int id) throws ServiceException{
        try{
            MovieDao movieDao = DaoFactory.getInstance().getMovieDao();
            Movie movie = movieDao.findById(id).get();
            if(!movie.getRatedEnough() && movie.getFeedbackAmount()>=ENOUGH_RATED_AMOUNT){
                FeedbackDao feedbackDao = DaoFactory.getInstance().getFeedbackDao();
                List<Feedback> feedbacks = feedbackDao.findByMovieId(id);
                UserDao userDao = DaoFactory.getInstance().getUserDao();
                UserService userService = ServiceFactory.getInstance().getUserService();

                double averageRating = movie.getAverageRating();
                for(Feedback feedback : feedbacks){
                    int userId = feedback.getUserId();
                    int rating = feedback.getRating();
                    User user = userDao.findById(userId).get();
                    int score = user.getScore();
                    score += Math.abs(averageRating-rating)<RATING_BORDERS ? 5 : -5;
                    userService.updateUserScoreById(userId, score);
                }
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
