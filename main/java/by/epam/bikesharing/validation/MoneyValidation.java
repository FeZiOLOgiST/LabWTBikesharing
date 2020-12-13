package by.epam.bikesharing.validation;

import java.math.BigDecimal;

public class MoneyValidation {

    private static final String MONEY_AMOUNT_REGEX = "^[0-9]+(\\.[0-9]{2})?$";
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");

    public static boolean isValid(String amount) {
        return amount.matches(MONEY_AMOUNT_REGEX);
    }

    public static boolean isNotZero(BigDecimal amount) {
        return !(amount.compareTo(MIN_AMOUNT) < 0);
    }
}