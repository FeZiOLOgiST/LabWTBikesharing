package by.epam.bikesharing.service.rent;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

class RentFinishedTransactionTest {

    @Test
    void calculateRideCost() {
        BigDecimal hourCost = new BigDecimal("5");
        BigDecimal rideTime = new BigDecimal(1574946934 - 1571946934);

        BigDecimal rs = rideTime.multiply(hourCost).divide(new BigDecimal("3600000"), 2, RoundingMode.HALF_UP);
        System.out.println();
    }
}