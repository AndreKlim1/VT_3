package dao.mapper.impl;

import dao.mapper.Column;
import dao.mapper.RowMapper;
import entity.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackRowMapper implements RowMapper<Feedback> {

    @Override
    public Feedback map(ResultSet resultSet) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(resultSet.getInt(Column.ID));
        feedback.setRating(resultSet.getInt(Column.FEEDBACK_RATING));
        feedback.setContent(resultSet.getString(Column.FEEDBACK_CONTENT));
        feedback.setUserId(resultSet.getInt(Column.USER_ID));
        feedback.setMovieId(resultSet.getInt(Column.MOVIE_ID));
        return feedback;
    }
}
