package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.Rent;
import org.junit.jupiter.api.Test;

class RentDAOTest {

    @Test
    void userCurrentRent() {
        Rent rent = new RentDao().userCurrentRent(5);

        System.out.println();
    }
}