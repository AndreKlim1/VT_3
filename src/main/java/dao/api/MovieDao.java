package dao.api;

import dao.Dao;
import entity.Movie;
import exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends Dao<Movie> {

    Optional<Movie> findByName(String name) throws DaoException;

    List<Movie> findByAverageRating(double from, double to) throws DaoException;

    List<Movie> findByFeedbackAmount(int from, int to) throws DaoException;

    void updateMovieById(int id, Movie movie) throws DaoException;
    void updateAverageRatingById(int id, double averageRating) throws DaoException;

    void updateFeedbackAmountById(int id, int feedbackAmount) throws DaoException;
}
