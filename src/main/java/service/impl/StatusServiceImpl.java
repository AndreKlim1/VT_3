package service.impl;

import dao.DaoFactory;
import dao.api.StatusDao;
import entity.Status;
import exceptions.DaoException;
import exceptions.ServiceException;
import service.api.StatusService;

import java.util.List;
import java.util.Optional;

public class StatusServiceImpl implements StatusService {

    @Override
    public Optional<Status> retrieveStatusById(int statusId) throws ServiceException {
        try {
            StatusDao statusDao = DaoFactory.getInstance().getStatusDao();
            Optional<Status> result;
            result = statusDao.findById(statusId);
            return result;
        } catch (DaoException e) {

            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Optional<Status> retrieveStatusByName(String statusName) throws ServiceException {
        try {
            StatusDao statusDao = DaoFactory.getInstance().getStatusDao();
            Optional<Status> result;
            result = statusDao.findByName(statusName);
            return result;
        } catch (DaoException e) {

            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Status> retrieveStatusByScore(int score) throws ServiceException {
        try {
            StatusDao statusDao = DaoFactory.getInstance().getStatusDao();
            List<Status> statuses= statusDao.findAll();
            int statusId=0;
            int statusScore=-40;
            for (Status status : statuses){
                if(status.getScore()<score && status.getScore()>=statusScore) {
                    statusId = status.getId();
                    statusScore = status.getScore();
                }
            }
            return statusDao.findById(statusId);
        } catch (DaoException e) {

            throw new ServiceException(e.getMessage(), e);
        }
    }

}
