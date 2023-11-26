package dao.api;

import dao.Dao;
import entity.Role;
import exceptions.DaoException;

import java.util.Optional;

public interface RoleDao extends Dao<Role> {
    Optional<Role> findByName(String name) throws DaoException;
}
