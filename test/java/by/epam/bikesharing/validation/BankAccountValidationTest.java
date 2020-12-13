package by.epam.bikesharing.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountValidationTest {

    @Test
    void isValidCvv() {
        assertTrue(BankAccountValidation.isValidCvv("666"));

        assertFalse(BankAccountValidation.isValidCvv("00O"));
    }
}