package by.epam.bikesharing.service.rent;

import by.epam.bikesharing.dao.BikeDao;
import by.epam.bikesharing.dao.EntityTransaction;
import by.epam.bikesharing.dao.RentDao;
import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.Rent;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.TransactionException;

import java.sql.Timestamp;

public class RentStartedTransaction {

    private User user;
    private Bike bike;
    private Rent rent;

    public User assignBikeToUser(long userId, long bikeId) throws TransactionException {
        getDatabaseEntities(userId, bikeId);
        assignEntitiesProperties(userId, bikeId);
        rent = getNewRent(userId, bikeId);
        if (transactBusiness()) {
            return user;
        }
        throw new TransactionException();
    }

    private void getDatabaseEntities(long userId, long bikeId) {
        UserDao userDAO = new UserDao();
        BikeDao bikeDAO = new BikeDao();
        user = userDAO.findEntityById(userId);
        bike = bikeDAO.findEntityById(bikeId);
        userDAO.closeConnection();
        bikeDAO.closeConnection();
    }

    private Rent getNewRent(long userId, long bikeId) {
        Rent rent = new Rent();
        rent.setUserId(userId);
        rent.setBikeId(bikeId);
        rent.setStart(new Timestamp(System.currentTimeMillis()));
        return rent;
    }

    private void assignEntitiesProperties(long userId, long bikeId) {
        user.setBikeId(bikeId);
        bike.setUserId(userId);
        bike.setSpotId(0);
    }

    private boolean transactBusiness() {
        boolean result = false;
        UserDao userDAO = new UserDao();
        BikeDao bikeDAO = new BikeDao();
        RentDao rentDAO = new RentDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(rentDAO, userDAO, bikeDAO);
        if (rentDAO.create(rent) && userDAO.update(user) && bikeDAO.update(bike)) {
            transaction.commit();
            result = true;
        } else {
            transaction.rollback();
        }
        transaction.end();
        return result;
    }
}
