package dao.impl;

import dao.AbstractDao;
import dao.Table;
import dao.api.StatusDao;
import dao.mapper.Column;
import dao.mapper.RowMapperFactory;
import entity.Status;
import exceptions.DaoException;

import java.util.Optional;

public class StatusDaoImpl extends AbstractDao<Status> implements StatusDao {
    private static final String SAVE_STATUS_QUERY = "INSERT INTO " + Table.STATUS + " ("+ Column.STATUS_NAME +", "+Column.STATUS_SCORE+") VALUES (?)";
    private static final String FIND_STATUS_BY_NAME_QUERY = "SELECT * FROM " + Table.STATUS + " WHERE "+Column.STATUS_NAME+"=?";
    private static final String FIND_STATUS_BY_SCORE_QUERY = "SELECT * FROM " + Table.STATUS + " WHERE "+Column.STATUS_SCORE+"=?";

    public StatusDaoImpl() {
        super(RowMapperFactory.getInstance().getStatusRowMapper(), Table.STATUS);
    }

    @Override
    public int save(Status status) throws DaoException {
        return executeInsertQuery(SAVE_STATUS_QUERY, status.getName(), status.getScore());
    }

    @Override
    public Optional<Status> findByName(String name) throws DaoException {
        return executeQueryForSingleResult(FIND_STATUS_BY_NAME_QUERY, name);
    }

    @Override
    public Optional<Status> findByScore(int score) throws DaoException {
        return executeQueryForSingleResult(FIND_STATUS_BY_SCORE_QUERY, score);
    }
}

