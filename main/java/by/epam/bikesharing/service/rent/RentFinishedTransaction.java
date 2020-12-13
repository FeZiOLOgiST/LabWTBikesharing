package by.epam.bikesharing.service.rent;

import by.epam.bikesharing.constant.ServiceConstant;
import by.epam.bikesharing.dao.*;
import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.Rent;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.TransactionException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

public class RentFinishedTransaction {

    private User user;
    private Bike bike;
    private Rent rent;

    public User finishRent(long userId, long bikeId, long spotId) throws TransactionException {
        getDatabaseEntities(userId, bikeId);
        assignEntitiesProperties(spotId);
        rent.setEnd(new Timestamp(System.currentTimeMillis()));
        setMoneyProperties(calculateRideCost(bike.getModel().getCost()));
        if (transactBusiness()) {
            return user;
        }
        throw new TransactionException();
    }

    private void getDatabaseEntities(long userId, long bikeId) {
        AbstractDao[] daos = {new UserDao(), new BikeDao(), new RentDao()};
        user = (User) daos[0].findEntityById(userId);
        bike = (Bike) daos[1].findEntityById(bikeId);
        rent = ((RentDao) daos[2]).userCurrentRent(userId);
        for (AbstractDao dao : daos) {
            dao.closeConnection();
        }
    }

    private void assignEntitiesProperties(long spotId) {
        user.setBikeId(0);
        bike.setUserId(0);
        bike.setSpotId(spotId);
    }

    private BigDecimal calculateRideCost(BigDecimal hourCost) {
        BigDecimal rideTime = new BigDecimal(rent.getEnd().getTime() - rent.getStart().getTime());
        return rideTime.multiply(hourCost).divide(ServiceConstant.MILLIS_IN_HOUR_COUNT, 2, RoundingMode.HALF_UP);
    }

    private void setMoneyProperties(BigDecimal rideCost) {
        rent.setCost(rideCost);
        user.setBalance(user.getBalance().subtract(rideCost));
    }

    private boolean transactBusiness() {
        boolean result = false;
        UserDao userDAO = new UserDao();
        BikeDao bikeDAO = new BikeDao();
        RentDao rentDAO = new RentDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(rentDAO, userDAO, bikeDAO);
        if (rentDAO.update(rent) && userDAO.update(user) && bikeDAO.update(bike)) {
            transaction.commit();
            result = true;
        } else {
            transaction.rollback();
        }
        transaction.end();
        return result;
    }
}
