package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.BaseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StringListDao extends AbstractDao {

    private static final String SELECT_BIKES_USER_NAMES =
        "SELECT Login FROM dbo.Bike LEFT JOIN dbo.[User] ON Bike.UserID = dbo.[User].UserID";
    private static final String SELECT_BIKES_SPOT_NAMES =
        "SELECT Address FROM dbo.Bike LEFT JOIN dbo.SharingSpot ON Bike.SpotID = SharingSpot.SpotID";

    @Override
    public List findAll() {
        return null;
    }

    public List<String> findBikesSpotNames() {
        List<String> spotNames = find(SELECT_BIKES_SPOT_NAMES);
        return spotNames;
    }

    public List<String> findBikesUserNames() {
        List<String> userNames = find(SELECT_BIKES_USER_NAMES);
        return userNames;
    }

    @Override
    List<String> find(String sqlQuery, String ... params) {
        List<String> entityList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlQuery);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entityList.add(getStringFromSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("SQL exception while trying to %s: %s.", sqlQuery, e));
        } finally {
            close(statement);
        }
        return entityList;
    }

    private String getStringFromSet(ResultSet resultSet) throws SQLException {
        String result = resultSet.getString(1);
        return (result == null) ? "NULL" : result;
    }

    @Override
    public BaseEntity findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    PreparedStatement getCreateStatement(BaseEntity entity) throws SQLException {
        return null;
    }

    @Override
    PreparedStatement getUpdateStatement(BaseEntity entity) throws SQLException {
        return null;
    }

    @Override
    BaseEntity getEntityFromSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
