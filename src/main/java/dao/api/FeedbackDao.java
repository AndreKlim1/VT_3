package dao.api;

import dao.Dao;
import entity.Feedback;
import entity.Movie;
import exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface FeedbackDao extends Dao<Feedback> {

    List<Feedback> findByMovieId(int movieId) throws DaoException;
    List<Feedback> findByUserId(int userId) throws DaoException;
    Optional<Feedback> findByUserAndMovieId(int userId, int movieId) throws DaoException;
    void updateFeedbackById(int id, Feedback feedback) throws DaoException;
}
