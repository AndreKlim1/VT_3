package service.api;

import entity.Status;
import exceptions.ServiceException;

import java.util.Optional;

public interface StatusService {

    Optional<Status> retrieveStatusById(int statusId) throws ServiceException;
    Optional<Status> retrieveStatusByName(String statusName) throws ServiceException;
    Optional<Status> retrieveStatusByScore(int score) throws ServiceException;
}
