package dao.api;

import entity.Status;
import exceptions.DaoException;

import java.util.Optional;

public interface StatusDao {
    Optional<Status> findByName(String name) throws DaoException;
    Optional<Status> findByScore(int score) throws DaoException;
}
