package dao.api;

import dao.Dao;
import entity.Status;
import exceptions.DaoException;

import java.util.Optional;

public interface StatusDao extends Dao<Status> {
    Optional<Status> findByName(String name) throws DaoException;
    Optional<Status> findByScore(int score) throws DaoException;
}
