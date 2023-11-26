package dao.impl;

import dao.AbstractDao;
import dao.Table;
import dao.api.UserDao;
import dao.mapper.Column;
import dao.mapper.RowMapperFactory;
import entity.User;
import exceptions.DaoException;

import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT * FROM " + Table.USER + " WHERE "+Column.USER_EMAIL+"=? and "+Column.USER_PASSWORD+"=SHA1(?)";
    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM " + Table.USER + " WHERE "+ Column.USER_EMAIL+ "=?";
    private static final String FIND_USER_BY_NICKNAME_QUERY = "SELECT * FROM " + Table.USER + " WHERE "+Column.USER_NICKNAME+"=?";
    private static final String UPDATE_USER_NICKNAME_BY_ID_QUERY = "UPDATE " + Table.USER + " SET "+Column.USER_NICKNAME+"=? WHERE "+Column.ID+"=?";

    private static final String UPDATE_USER_SCORE_BY_ID_QUERY = "UPDATE " + Table.USER + " SET "+Column.USER_SCORE+"=? WHERE "+Column.ID+"=?";

    private static final String UPDATE_USER_STATUS_ID_BY_ID_QUERY = "UPDATE " + Table.USER + " SET "+Column.STATUS_ID+"=? WHERE "+Column.ID+"=?";

    private static final String UPDATE_USER_BANNED_BY_ID_QUERY = "UPDATE " + Table.USER + " SET "+Column.USER_BANNED+"=? WHERE "+Column.ID+"=?";
    private static final String SAVE_USER_QUERY = "INSERT INTO " + Table.USER + " ("+Column.USER_NICKNAME+", "+ Column.USER_EMAIL+", "+Column.USER_PASSWORD+", "+Column.USER_SCORE+", "+Column.USER_BANNED+", "+Column.ROLE_ID+", "+Column.STATUS_ID+") VALUES (?, ?, ?, ?, ?, ?, ?)";

    public UserDaoImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), Table.USER);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_EMAIL_AND_PASSWORD_QUERY, email, DigestUtils.sha1Hex(password));
    }


    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_EMAIL_QUERY, email);
    }

    @Override
    public Optional<User> findByNickname(String nickname) throws DaoException {
        return executeQueryForSingleResult(FIND_USER_BY_NICKNAME_QUERY, nickname);
    }

    @Override
    public void updateNicknameById(int id, String nickname) throws DaoException {
        executeUpdateQuery(UPDATE_USER_NICKNAME_BY_ID_QUERY, nickname, id);
    }

    @Override
    public void updateStatusIdById(int id, int statusId) throws DaoException {
        executeUpdateQuery(UPDATE_USER_STATUS_ID_BY_ID_QUERY, statusId, id);
    }

    @Override
    public void updateScoreById(int id, int score) throws DaoException {
        executeUpdateQuery(UPDATE_USER_SCORE_BY_ID_QUERY, score, id);
    }

    @Override
    public void updateBannedById(int id, boolean banned) throws DaoException {
        executeUpdateQuery(UPDATE_USER_BANNED_BY_ID_QUERY, banned, id);
    }


    @Override
    public int save(User user) throws DaoException {
        return executeInsertQuery(SAVE_USER_QUERY, user.getNickname(), user.getEmail(), user.getPassword(), user.getScore(), user.getBanned(),
                user.getRoleId(), user.getStatusId());
    }
}
