package by.epam.bikesharing.validation;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyValidationTest {

    @Test
    void isValid() {
        assertTrue(MoneyValidation.isValid("4.20"));
        assertTrue(MoneyValidation.isValid("0.69"));
        assertTrue(MoneyValidation.isValid("88.88"));
        assertTrue(MoneyValidation.isValid("0"));
        assertTrue(MoneyValidation.isValid("42"));
        assertFalse(MoneyValidation.isValid("66.6"));
        assertFalse(MoneyValidation.isValid("0.228"));
    }

    @Test
    void isNotZero() {
        assertTrue(MoneyValidation.isNotZero(new BigDecimal("0.01")));

        assertFalse(MoneyValidation.isNotZero(new BigDecimal("0.0")));
    }
}