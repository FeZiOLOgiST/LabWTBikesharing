package by.epam.bikesharing.service.pages;

import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.Bike;
import by.epam.bikesharing.entity.BikeModel;
import by.epam.bikesharing.entity.Spot;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BikesPageLogic {

    public void handleBikesPageRequest(HttpServletRequest request) {

        List<Bike> bikes = PagesLogic.searchBikes();
        List<BikeModel> models = PagesLogic.searchModels();
        List<Spot> spots = PagesLogic.searchSpots();
        List<String> spotNames = PagesLogic.getSpotNames();
        List<String> userNames = PagesLogic.getUserNames();
        request.setAttribute(ParameterName.BIKES, bikes);
        request.setAttribute(ParameterName.MODELS, models);
        request.setAttribute(ParameterName.SPOTS, spots);
        request.setAttribute(ParameterName.SPOT_NAMES, spotNames);
        request.setAttribute(ParameterName.USER_NAMES, userNames);
    }
}
