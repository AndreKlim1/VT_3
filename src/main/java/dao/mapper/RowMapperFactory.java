package dao.mapper;


import dao.mapper.impl.*;
import entity.*;

public class RowMapperFactory {
    public static RowMapperFactory getInstance() {
        return Holder.INSTANCE;
    }

    private final RowMapper<User> userRowMapper = new UserRowMapper();
    private final RowMapper<Role> roleRowMapper = new RoleRowMapper();
    private final RowMapper<Movie> movieRowMapper = new MovieRowMapper();
    private final RowMapper<Status> statusRowMapper = new StatusRowMapper();
    private final RowMapper<Feedback> feedbackRowMapper = new FeedbackRowMapper();

    public RowMapper<User> getUserRowMapper() {
        return userRowMapper;
    }

    public RowMapper<Role> getRoleRowMapper() {
        return roleRowMapper;
    }

    public RowMapper<Movie> getMovieRowMapper() {
        return movieRowMapper;
    }

    public RowMapper<Status> getStatusRowMapper() {
        return statusRowMapper;
    }

    public RowMapper<Feedback> getFeedbackRowMapper() {
        return feedbackRowMapper;
    }

    private static class Holder {
        private static final RowMapperFactory INSTANCE = new RowMapperFactory();
    }
}

