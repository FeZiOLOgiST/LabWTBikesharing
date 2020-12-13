package by.epam.bikesharing.service;

import by.epam.bikesharing.dao.BikeDao;
import by.epam.bikesharing.dao.ModelDao;
import by.epam.bikesharing.dao.SpotDao;
import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.BikeModel;
import by.epam.bikesharing.entity.Spot;

public class BikeLogic {

    public boolean addBike(String serialNumber, String modelName, String address) {

        Bike bike = new Bike();
        ModelDao modelDao = new ModelDao();
        BikeModel model = modelDao.findModelByName(modelName);
        modelDao.closeConnection();
        bike.setSerialNumber(serialNumber);
        bike.setModel(model);
        bike.setSpotId(getSpotId(address));
        BikeDao dao = new BikeDao();
        dao.create(bike);
        dao.closeConnection();
        return true;
    }

    public boolean editBikeSpot(Long id, String address) {
        Bike bike = new Bike();
        bike.setId(id);
        bike.setSpotId(getSpotId(address));
        BikeDao dao = new BikeDao();
        dao.update(bike);
        dao.closeConnection();
        return true;
    }

    private Long getSpotId(String address) {
        SpotDao spotDao = new SpotDao();
        Spot spot = spotDao.findSpotByAddress(address);
        spotDao.closeConnection();
        return (spot == null)? 0 : spot.getId();
    }
}