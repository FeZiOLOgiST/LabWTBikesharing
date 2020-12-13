package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.BaseEntity;
import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.BikeModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class BikeDao extends AbstractDao {

    private static final String SELECT_ALL_BIKES =
        "SELECT * FROM dbo.[Bike] JOIN dbo.[Model] ON Bike.ModelID = Model.ModelID";
    private static final String SELECT_ALL_BIKES_SPOTS =
        "SELECT * FROM dbo.[Bike] JOIN dbo.[Model] ON Bike.ModelID = Model.ModelID JOIN dbo.[SharingSpot] ON Bike.SpotID = SharingSpot.SpotID";
    private static final String SELECT_BIKE_BY_ID =
        "SELECT * FROM dbo.[Bike] JOIN dbo.[Model] ON Bike.ModelID = Model.ModelID WHERE BikeID = ?";
    private static final String SELECT_AVAILABLE_BIKES =
        "SELECT * FROM dbo.[Bike] JOIN dbo.[Model] ON Bike.ModelID = Model.ModelID WHERE SpotID IS NOT NULL";
    private static final String DELETE_BIKE = "DELETE FROM dbo.[Bike] WHERE BikeID = ?";
    private static final String UPDATE_BIKE = "UPDATE dbo.[Bike] SET UserID=?, SpotID=? WHERE BikeID=?";
    private static final String INSERT_BIKE = "INSERT INTO dbo.[Bike] (ModelID, SpotID, SerialNumber) VALUES (?, ?, ?)";

    @Override
    public List<Bike> findAll() {
        return find(SELECT_ALL_BIKES);
    }

//    public List<Bike> findBikes() {
//        return find(SELECT_ALL_BIKES_SPOTS);
//    }

    public List<Bike> findAvailable() {
        return find(SELECT_AVAILABLE_BIKES);
    }

    @Override
    public Bike findEntityById(long id) {
        List<Bike> bikes = find(SELECT_BIKE_BY_ID, Long.toString(id));
        return bikes.get(0);
        //return (!bikes.isEmpty())? Optional.of(bikes.get(0)) : Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return delete(DELETE_BIKE, id);
    }

    @Override
    PreparedStatement getCreateStatement(BaseEntity entity) throws SQLException {
        Bike bike = (Bike) entity;
        PreparedStatement statement = getPreparedStatement(INSERT_BIKE);
        statement.setLong(1, bike.getModel().getId());
        statement.setLong(2, bike.getSpotId());
        statement.setString(3, bike.getSerialNumber());
        return statement;
    }

    @Override
    PreparedStatement getUpdateStatement(BaseEntity entity) throws SQLException {
        Bike bike = (Bike) entity;
        PreparedStatement statement = getPreparedStatement(UPDATE_BIKE);
        if (bike.getSpotId() == 0) {
            statement.setLong(1, bike.getUserId());
            statement.setNull(2, Types.INTEGER);
        } else {
            statement.setNull(1, Types.INTEGER);
            statement.setLong(2, bike.getSpotId());
        }
        statement.setLong(3, bike.getId());
        return statement;
    }

    @Override
    Bike getEntityFromSet(ResultSet resultSet) throws SQLException {
        Bike bike = new Bike();
        bike.setId(resultSet.getLong("BikeID"));
        bike.setUserId(resultSet.getLong("UserID"));
        bike.setSpotId(resultSet.getLong("SpotID"));
        bike.setSerialNumber(resultSet.getString("SerialNumber"));
        if (resultSet.getMetaData().getColumnCount() > 5) {
            bike.setModel(new BikeModel(
                    resultSet.getLong("ModelID"),
                    resultSet.getString("Name"),
                    resultSet.getString("ImageURL"),
                    resultSet.getBigDecimal("Cost")));
        }
        return bike;
    }
}
