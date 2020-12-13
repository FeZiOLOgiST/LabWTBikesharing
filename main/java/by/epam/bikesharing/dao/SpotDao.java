package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.BaseEntity;
import by.epam.bikesharing.entity.Spot;
import by.epam.bikesharing.sqlpool.ConnectionPool;
import by.epam.bikesharing.sqlpool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpotDao extends AbstractDao {

    private static final String SELECT_ALL_SPOTS = "SELECT * FROM dbo.[SharingSpot]";
    private static final String SELECT_SPOT_BY_ADDRESS = "SELECT * FROM dbo.[SharingSpot] WHERE Address=?";
    private static final String SELECT_SPOT_NAMES =
        "SELECT Address FROM dbo.[SharingSpot] JOIN dbo.[Bike] ON SharingSpot.SpotID = Bike.SpotID";

    @Override
    public List<Spot> findAll() {
        List<Spot> spots = new ArrayList<>();
        ProxyConnection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SPOTS);
            while (resultSet.next()) {
                Spot spot = new Spot(
                        resultSet.getLong("SpotID"),
                        resultSet.getString("Address"),
                        resultSet.getDouble("Latitude"),
                        resultSet.getDouble("Longitude"));
                spots.add(spot);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return spots;
    }

    public List<Spot> findSpotNames() {
        return find(SELECT_SPOT_NAMES);
    }

    @Override
    public Spot findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    public Spot findSpotByAddress(String address) {
        List<Spot> spots = find(SELECT_SPOT_BY_ADDRESS, address);
        return spots.isEmpty()? null : spots.get(0);
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
    Spot getEntityFromSet(ResultSet resultSet) throws SQLException {
        Spot spot = new Spot();
        spot.setAddress(resultSet.getString("Address"));
        if (resultSet.getMetaData().getColumnCount() > 1) {
            spot.setId(resultSet.getLong("SpotID"));
            spot.setLat(resultSet.getDouble("Latitude"));
            spot.setLng(resultSet.getDouble("Longitude"));
        }
        return spot;
    }
}
