package dao.mapper.impl;

import dao.mapper.Column;
import dao.mapper.RowMapper;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Column.ID));
        user.setNickname(resultSet.getString(Column.USER_NICKNAME));
        user.setEmail(resultSet.getString(Column.USER_EMAIL));
        user.setPassword(resultSet.getString(Column.USER_EMAIL));
        user.setScore(resultSet.getInt(Column.USER_SCORE));
        user.setRoleId(resultSet.getInt(Column.ROLE_ID));
        user.setStatusId(resultSet.getInt(Column.STATUS_ID));
        user.setBanned(resultSet.getInt(Column.USER_BANNED) == 1);
        return user;
    }
}
