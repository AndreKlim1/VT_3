package dao.impl;

import dao.AbstractDao;
import dao.Table;
import dao.api.MovieDao;
import dao.mapper.Column;
import dao.mapper.RowMapperFactory;
import entity.Movie;
import exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    private static final String FIND_MOVIES_BY_AVERAGE_RATING_QUERY = "SELECT * FROM " + Table.MOVIE + " WHERE "+Column.MOVIE_AVERAGE_RATING+" BETWEEN ? AND ? ";

    private static final String FIND_MOVIES_BY_FEEDBACK_AMOUNT_QUERY = "SELECT * FROM " + Table.MOVIE + " WHERE "+Column.MOVIE_FEEDBACK_AMOUNT+" BETWEEN ? AND ? ";

    private static final String FIND_MOVIE_BY_NAME_QUERY = "SELECT * FROM " + Table.MOVIE + " WHERE "+ Column.MOVIE_NAME +"=? ";

    private static final String UPDATE_MOVIE_AVERAGE_RATING_BY_ID_QUERY = "UPDATE " + Table.USER + " SET "+Column.MOVIE_AVERAGE_RATING+"=? WHERE "+Column.ID+"=?";

    private static final String SAVE_MOVIE_QUERY = "INSERT INTO " + Table.MOVIE + " ("+Column.MOVIE_NAME+", "+Column.MOVIE_DESCRIPTION+", "+Column.MOVIE_IMAGE+", "+ Column.MOVIE_AVERAGE_RATING+", "+Column.MOVIE_FEEDBACK_AMOUNT+", "+Column.MOVIE_RATED_ENOUGH+") VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_MOVIE_BY_ID_QUERY = "UPDATE " + Table.MOVIE + " SET "+Column.MOVIE_NAME+"=? "+Column.MOVIE_DESCRIPTION+"=? "+Column.MOVIE_IMAGE+"=? "+ Column.MOVIE_AVERAGE_RATING+"=? "+Column.MOVIE_FEEDBACK_AMOUNT+"=? "+Column.MOVIE_RATED_ENOUGH+"=? WHERE "+Column.ID+"=?";
    public MovieDaoImpl() {
        super(RowMapperFactory.getInstance().getMovieRowMapper(), Table.MOVIE);
    }

    @Override
    public int save(Movie movie) throws DaoException {

    }

    @Override
    public Optional<Movie> findByName(String name) throws DaoException {
        return executeQueryForSingleResult(FIND_MOVIE_BY_NAME_QUERY, name);
    }

    @Override
    public List<Movie> findByAverageRating(double from, double to) throws DaoException {
        return executeQuery(FIND_MOVIES_BY_AVERAGE_RATING_QUERY, from, to);
    }

    @Override
    public List<Movie> findByFeedbackAmount(int from, int to) throws DaoException {
        return executeQuery(FIND_MOVIES_BY_FEEDBACK_AMOUNT_QUERY, from, to);
    }

    @Override
    public void updateMovieById(int id, Movie movie) throws DaoException {

    }

    @Override
    public void updateAverageRatingById(int id, double averageRating) throws DaoException {

    }
}
