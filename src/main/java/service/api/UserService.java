package service.api;

import entity.User;
import exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> login(String email, String password) throws ServiceException;


    boolean register(String name, String surname,  String email, String phone, String password) throws ServiceException;


    Optional<User> retrieveUserById(int userId) throws ServiceException;

    List<User> retrieveUsersByStatus(int statusId) throws ServiceException;

    boolean updateUserNicknameById(int id, String nickname) throws ServiceException;

    boolean updateUserScoreById(int id, int score) throws ServiceException;

    boolean updateUserStatusById(int id, int statusId) throws ServiceException;

    boolean banUsersById(int id, boolean banned) throws ServiceException;
}
