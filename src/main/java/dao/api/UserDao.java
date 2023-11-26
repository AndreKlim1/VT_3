package dao.api;

import dao.Dao;
import entity.User;
import exceptions.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    Optional<User> findByNickname(String nickname) throws DaoException;

    void updateNicknameById(int id, String nickname) throws DaoException;
    void updateStatusIdById(int id, int statusId) throws DaoException;
    void updateScoreById(int id, int score) throws DaoException;
    void updateBannedById(int id, boolean banned) throws DaoException;
}