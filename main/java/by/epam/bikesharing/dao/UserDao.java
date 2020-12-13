package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.BaseEntity;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.service.PasswordHash;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class UserDao extends AbstractDao {

    private static final BigDecimal INITIAL_USER_BALANCE = new BigDecimal("0");
    private static final String INITIAL_USER_ROLE = "user";
    private static final String SELECT_ALL_USERS = "SELECT * FROM dbo.[User]";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM dbo.[User] WHERE Login=?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM dbo.[User] WHERE Email=?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM dbo.[User] WHERE UserID=?";
    private static final String DELETE_USER = "DELETE FROM dbo.[User] WHERE UserID=?";
    private static final String INSERT_USER =
        "INSERT INTO dbo.[User] (Email, Login, PasswordHash, PasswordSalt, Balance, Role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER =
        "UPDATE dbo.[User] SET BikeID=?, Email=?, Login=?, PasswordHash=?, PasswordSalt=?, Balance=?, Role=? WHERE UserID=?";
    private static final String UPDATE_USER_IMAGE = "UPDATE dbo.[User] SET Image=? WHERE UserID=?";

    @Override
    public List<User> findAll() {
        return find(SELECT_ALL_USERS);
    }

    public User findUserByLogin(String login) {
        User user = null;
        List users = find(SELECT_USER_BY_LOGIN, login);
        if (!users.isEmpty()) user = (User) users.get(0);
        return user;
    }

    public User findUserByEmail(String email) {
        User user = null;
        List users = find(SELECT_USER_BY_EMAIL, email);
        if (!users.isEmpty()) user = (User) users.get(0);
        return user;
    }

    @Override
    public User findEntityById(long id) {
        List users = find(SELECT_USER_BY_ID, Long.toString(id));
        return (User) users.get(0); //(!users.isEmpty())? Optional.of(users.get(0)) : Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return delete(DELETE_USER, id);
    }

    public boolean updateUserImage(long id, String image) {
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(UPDATE_USER_IMAGE);
            statement.setString(1, image);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to update user[id=%s] image: %s", id, e));
        } finally {
            close(statement);
        }
        return false;
    }

    @Override
    PreparedStatement getCreateStatement(BaseEntity entity) throws SQLException {
        User user = (User) entity;
        PreparedStatement statement = getPreparedStatement(INSERT_USER);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getLogin());
        statement.setBytes(3, user.getPasswordHash().getHash());
        statement.setBytes(4, user.getPasswordHash().getSalt());
        statement.setBigDecimal(5, INITIAL_USER_BALANCE);
        statement.setString(6, INITIAL_USER_ROLE);
        return statement;
    }

    @Override
    PreparedStatement getUpdateStatement(BaseEntity entity) throws SQLException {
        User user = (User) entity;
        PreparedStatement statement = getPreparedStatement(UPDATE_USER);
        if (user.getBikeId() == 0)
            statement.setNull(1, Types.INTEGER);
        else
            statement.setLong(1, user.getBikeId());
        statement.setLong(1, user.getBikeId());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getLogin());
        statement.setBytes(4, user.getPasswordHash().getHash());
        statement.setBytes(5, user.getPasswordHash().getSalt());
        statement.setBigDecimal(6, user.getBalance());
        statement.setString(7, user.getRole());
        statement.setLong(8, user.getId());
        return statement;
    }

    @Override
    User getEntityFromSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("UserID"));
        user.setBikeId(resultSet.getLong("BikeID"));
        user.setEmail(resultSet.getString("Email"));
        user.setLogin(resultSet.getString("Login"));
        user.setRole(resultSet.getString("Role"));
        user.setBalance(resultSet.getBigDecimal("Balance"));
        user.setImage(resultSet.getString("Image"));
        user.setLocale(resultSet.getString("locale"));
        user.setPasswordHash(new PasswordHash(
                resultSet.getBytes("PasswordHash"),
                resultSet.getBytes("PasswordSalt")
        ));
        return user;
    }
}