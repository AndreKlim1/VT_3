package service.api;

import entity.Role;
import exceptions.ServiceException;

import java.util.Optional;

public interface RoleService {

    Optional<Role> retrieveRoleById(int roleId) throws ServiceException;

    Optional<Role> retrieveRoleByRoleName(String roleName) throws ServiceException;
}
