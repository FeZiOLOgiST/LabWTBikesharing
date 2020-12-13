package by.epam.bikesharing.service.pages;

import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.dao.BikeDao;
import by.epam.bikesharing.dao.RentDao;
import by.epam.bikesharing.dao.SpotDao;
import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.Rent;
import by.epam.bikesharing.entity.Spot;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MainRentPageLogic {

    public String getUserMainPage(User user, HttpServletRequest request) {

        String page;
        if (user.getBikeId() == 0) {
            List<Spot> spots = (new SpotDao()).findAll();
            request.setAttribute(ParameterName.SPOTS, spots);
            List<Bike> bikes = (new BikeDao()).findAvailable();
            request.setAttribute(ParameterName.BIKES, bikes);
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("start", getStartTime(user));
            page = ConfigurationManager.getProperty("path.page.ride");
        }
        return page;
    }

    private String getStartTime(User user) {
        RentDao rentDAO = new RentDao();
        Rent rent = rentDAO.userCurrentRent(user.getId());
        rentDAO.closeConnection();
        return rent.getStart().toString();
    }
}