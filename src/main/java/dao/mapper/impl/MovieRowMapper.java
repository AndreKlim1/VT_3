package dao.mapper.impl;

import dao.mapper.Column;
import dao.mapper.RowMapper;
import entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie map(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt(Column.ID));
        movie.setName(resultSet.getString(Column.MOVIE_NAME));
        movie.setDescription(resultSet.getString(Column.MOVIE_DESCRIPTION));
        movie.setImage(resultSet.getString(Column.MOVIE_IMAGE));
        movie.setAverageRating(resultSet.getDouble(Column.MOVIE_AVERAGE_RATING));
        movie.setRatedEnough(resultSet.getInt(Column.MOVIE_RATED_ENOUGH)==1);
        movie.setFeedbackAmount(resultSet.getInt(Column.MOVIE_FEEDBACK_AMOUNT));

        return movie;
    }
}
