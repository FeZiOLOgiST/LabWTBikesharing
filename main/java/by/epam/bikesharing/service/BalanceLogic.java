package by.epam.bikesharing.service;

import by.epam.bikesharing.dao.CardDao;
import by.epam.bikesharing.dao.EntityTransaction;
import by.epam.bikesharing.dao.UserDao;
import by.epam.bikesharing.entity.Card;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.NotEnoughMoneyException;
import by.epam.bikesharing.exception.TransactionException;

import java.math.BigDecimal;

public class BalanceLogic {

    private User user;
    private Card card;

    public User replenish(long userId, long cardId, BigDecimal amount) throws NotEnoughMoneyException, TransactionException {
        getEntitiesById(userId, cardId);
        BigDecimal userBalance = user.getBalance();
        BigDecimal cardBalance = card.getBalance();
        if (cardBalance.compareTo(amount) < 0) {
            throw new NotEnoughMoneyException(); //TODO
        }
        BigDecimal newUserBalance = userBalance.add(amount);
        BigDecimal newCardBalance = cardBalance.subtract(amount);
        user.setBalance(newUserBalance);
        card.setBalance(newCardBalance);
        if (transactBusiness()) {
            return user;
        }
        throw new TransactionException();
    }

    private void getEntitiesById(long userId, long cardId) {
        UserDao userDAO = new UserDao();
        CardDao cardDAO = new CardDao();
        user = userDAO.findEntityById(userId);
        card = cardDAO.findEntityById(cardId);
        userDAO.closeConnection();
        cardDAO.closeConnection();
    }

    private boolean transactBusiness() {
        boolean result = false;
        UserDao userDAO = new UserDao();
        CardDao cardDAO = new CardDao();
        EntityTransaction transaction = new EntityTransaction();
        transaction.begin(userDAO, cardDAO);
        if (userDAO.update(user) && cardDAO.update(card)) {
            transaction.commit();
            result = true;
        } else {
            transaction.rollback();
        }
        transaction.end();
        return result;
    }
}