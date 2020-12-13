package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.BaseEntity;
import by.epam.bikesharing.entity.BikeModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModelDao extends AbstractDao {

    private static final String SELECT_ALL_MODELS = "SELECT * FROM dbo.[Model]";
    private static final String SELECT_MODEL_BY_ID = "SELECT * FROM dbo.[Model] WHERE ModelID=?";
    private static final String CREATE_MODEL = "INSERT INTO [dbo].Model (Name, ImageURL, Cost) VALUES (?, ?, ?)";
    private static final String UPDATE_MODEL = "UPDATE [dbo].Model SET Cost=? WHERE ModelID=?";
    private static final String DELETE_MODEL = "";
    private static final String SELECT_MODEL_BY_NAME = "SELECT * FROM dbo.[Model] WHERE Name=?";

    @Override
    public List findAll() {
        return find(SELECT_ALL_MODELS);
    }

    @Override
    public BaseEntity findEntityById(long id) {
        return null;
    }


    public BikeModel findModelByName(String modelName) {
        return (BikeModel) find(SELECT_MODEL_BY_NAME, modelName).get(0);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    PreparedStatement getCreateStatement(BaseEntity entity) throws SQLException {
        BikeModel model = (BikeModel) entity;
        PreparedStatement statement = getPreparedStatement(CREATE_MODEL);
        statement.setString(1, model.getName());
        statement.setString(2, model.getImageUrl());
        statement.setBigDecimal(3, model.getCost());
        return statement;
    }

    @Override
    PreparedStatement getUpdateStatement(BaseEntity entity) throws SQLException {
        BikeModel model = (BikeModel) entity;
        PreparedStatement statement = getPreparedStatement(UPDATE_MODEL);
        statement.setBigDecimal(1, model.getCost());
        statement.setLong(2, model.getId());
        return statement;
    }

    @Override
    BikeModel getEntityFromSet(ResultSet resultSet) throws SQLException {
        BikeModel model = new BikeModel();
        model.setId(resultSet.getLong("ModelID"));
        model.setName(resultSet.getString("Name"));
        model.setImageUrl(resultSet.getString("ImageURL"));
        model.setCost(resultSet.getBigDecimal("Cost"));
        return model;
    }
}