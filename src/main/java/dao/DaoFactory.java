package dao;

import dao.impl.*;

public class DaoFactory {
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final RoleDaoImpl roleDao = new RoleDaoImpl();
    private final MovieDaoImpl movieDao = new MovieDaoImpl();
    private final FeedbackDaoImpl feedbackDao = new FeedbackDaoImpl();
    private final StatusDaoImpl statusDao = new StatusDaoImpl();

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public RoleDaoImpl getRoleDao() {
        return roleDao;
    }

    public MovieDaoImpl getMovieDao() {
        return movieDao;
    }

    public FeedbackDaoImpl getFeedbackDao() {
        return feedbackDao;
    }

    public StatusDaoImpl getStatusDao() {
        return statusDao;
    }

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return Holder.INSTANCE;
    }


    private static class Holder {
        static final DaoFactory INSTANCE = new DaoFactory();
    }
}