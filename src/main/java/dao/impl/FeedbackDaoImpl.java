package dao.impl;

import dao.AbstractDao;
import dao.Table;
import dao.api.FeedbackDao;
import dao.mapper.Column;
import dao.mapper.RowMapperFactory;
import entity.Feedback;
import exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class FeedbackDaoImpl extends AbstractDao<Feedback> implements FeedbackDao {

    private static final String UPDATE_FEEDBACK_BY_ID_QUERY = "UPDATE " + Table.FEEDBACK + " SET "+Column.FEEDBACK_RATING+"=?, "+Column.FEEDBACK_CONTENT+"=?, "+Column.USER_ID+"=?, "+ Column.MOVIE_ID+"=? WHERE "+Column.ID+"=?";
    private static final String FIND_FEEDBACK_BY_MOVIE_ID_QUERY = "SELECT * FROM " + Table.FEEDBACK + " WHERE "+ Column.MOVIE_ID+"=? ";
    private static final String FIND_FEEDBACK_BY_USER_ID_QUERY = "SELECT * FROM " + Table.FEEDBACK + " WHERE "+Column.USER_ID+"=? ";
    private static final String FIND_FEEDBACK_BY_USER_ID_AND_MOVIE_ID_QUERY = "SELECT * FROM " + Table.FEEDBACK + " WHERE "+Column.USER_ID+"=? and "+ Column.MOVIE_ID+"=? ";
    private static final String SAVE_FEEDBACK_QUERY = "INSERT INTO " + Table.FEEDBACK + " ("+Column.FEEDBACK_RATING+", "+Column.FEEDBACK_CONTENT+", "+Column.USER_ID+", "+ Column.MOVIE_ID+") VALUES (?, ?, ?, ?)";

    public FeedbackDaoImpl() {
        super(RowMapperFactory.getInstance().getFeedbackRowMapper(), Table.FEEDBACK);
    }
    @Override
    public int save(Feedback feedback) throws DaoException {
        return executeInsertQuery(SAVE_FEEDBACK_QUERY, feedback.getRating(), feedback.getContent(), feedback.getUserId(), feedback.getMovieId());
    }

    @Override
    public List<Feedback> findByMovieId(int movieId) throws DaoException {
        return executeQuery(FIND_FEEDBACK_BY_MOVIE_ID_QUERY, movieId);
    }

    @Override
    public List<Feedback> findByUserId(int userId) throws DaoException {
        return executeQuery(FIND_FEEDBACK_BY_USER_ID_QUERY, userId);
    }

    @Override
    public Optional<Feedback> findByUserAndMovieId(int userId, int movieId) throws DaoException {
        return executeQueryForSingleResult(FIND_FEEDBACK_BY_USER_ID_AND_MOVIE_ID_QUERY, userId, movieId);
    }

    @Override
    public void updateFeedbackById(int id, Feedback feedback) throws DaoException {
        executeUpdateQuery(UPDATE_FEEDBACK_BY_ID_QUERY, feedback.getRating(), feedback.getContent(), feedback.getUserId(), feedback.getMovieId(), id);
    }
}
