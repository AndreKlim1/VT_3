package service.impl;

import dao.DaoFactory;
import dao.api.FeedbackDao;
import dao.api.RoleDao;
import dao.api.StatusDao;
import dao.api.UserDao;
import dao.impl.RoleDaoImpl;
import entity.Feedback;
import entity.User;
import exceptions.DaoException;
import exceptions.ServiceException;
import service.ServiceFactory;
import service.api.StatusService;
import service.api.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final String ROLE="user";
    private static final String STATUS="Newcomer";
    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        if (email == null || password == null) {
            return Optional.empty();
        }

        if (!isEmailValid(email)) {
            return Optional.empty();
        }

        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            return userDao.findByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean register(String name, String email, String password) throws ServiceException {
        if (name == null || email == null || password == null) {
            return false;
        }

        if (!(isEmailValid(email) && isUserInformationValid(name, surname,  phone))) {
            return false;
        }
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            if (userDao.findByEmail(email).isPresent()) {
                return false;
            }
            if(userDao.findByNickname(name).isPresent()){
                return false;
            }
            RoleDao roleDao = DaoFactory.getInstance().getRoleDao();
            int roleId = roleDao.findByName(ROLE).get().getId();

            StatusDao statusDao = DaoFactory.getInstance().getStatusDao();
            int statusId = statusDao.findByName(STATUS).get().getId();

            User user = new User(0, name, email, password, 0, false, roleId, statusId);
            userDao.save(user);

            return true;
        } catch (DaoException e) {

            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> retrieveUserById(int userId) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            return userDao.findById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> retrieveUsersByStatus(int statusId) throws ServiceException {
        return null;
    }

    @Override
    public boolean updateUserNicknameById(int id, String nickname) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            userDao.updateNicknameById(id, nickname);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateUserScoreById(int id, int score) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            StatusService statusService = ServiceFactory.getInstance().getStatusService();
            int statusId = statusService.retrieveStatusByScore(score).get().getId();
            userDao.updateScoreAndStatusById(id, score, statusId);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateUserStatusById(int id, int statusId) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            StatusDao statusDao = DaoFactory.getInstance().getStatusDao();
            int score = statusDao.findById(statusId).get().getScore();
            userDao.updateScoreAndStatusById(id, score, statusId);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean banUsersById(int id, boolean banned) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            userDao.updateBannedById(id, banned);
            FeedbackDao feedbackDao = DaoFactory.getInstance().getFeedbackDao();
            List<Feedback> feedbacks = feedbackDao.findByUserId(id);
            for(Feedback feedback : feedbacks){
                feedbackDao.removeById(feedback.getId());
            }
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
