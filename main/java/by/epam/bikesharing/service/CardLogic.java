package by.epam.bikesharing.service;

import by.epam.bikesharing.constant.ServiceConstant;
import by.epam.bikesharing.dao.CardDao;
import by.epam.bikesharing.entity.Card;
import by.epam.bikesharing.validation.BankAccountValidation;
import by.epam.bikesharing.validation.DateValidation;
import by.epam.bikesharing.validation.NameValidation;

public class CardLogic {

    private static final String INVALID_NUMBER = "message.invalid_card_number";
    private static final String INVALID_FIRST_NAME = "message.invalid_first_name";
    private static final String INVALID_LAST_NAME = "message.invalid_last_name";
    private static final String INVALID_MONTH = "message.invalid_month";
    private static final String INVALID_YEAR = "message.invalid_year";
    private static final String INVALID_CVV = "message.invalid_cvv";

    public String addCard(long userId,
                          String number, String firstName, String lastName, String month, String year, String cvv) {
        if (!BankAccountValidation.isValidNumber(number)) return INVALID_NUMBER;
        if (!BankAccountValidation.isValidCvv(cvv)) return INVALID_CVV;
        if (!NameValidation.isValidName(firstName)) return INVALID_FIRST_NAME;
        if (!NameValidation.isValidName(lastName)) return INVALID_LAST_NAME;
        if (!DateValidation.isValidMonth(month)) return INVALID_MONTH;
        if (!DateValidation.isValidYear(year)) return INVALID_YEAR;
        Card card = new Card();
        card.setUserId(userId);
        card.setNumber(number);
        card.setFirstName(firstName);
        card.setLastName(lastName);
        card.setMonth(Integer.parseInt(month));
        card.setYear(Integer.parseInt(year));
        card.setCvv(new PasswordHash(cvv));
        CardDao cardDao = new CardDao();
        cardDao.create(card);
        cardDao.closeConnection();
        return ServiceConstant.UPDATE_SUCCESS;
    }
}
