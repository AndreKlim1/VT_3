package dao.mapper.impl;

import dao.mapper.Column;
import dao.mapper.RowMapper;
import entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusRowMapper implements RowMapper<Status> {

    @Override
    public Status map(ResultSet resultSet) throws SQLException {
        Status status = new Status();
        status.setId(resultSet.getInt(Column.ID));
        status.setName(resultSet.getString(Column.STATUS_NAME));
        status.setScore(resultSet.getInt(Column.STATUS_SCORE));
        return status;
    }
}
