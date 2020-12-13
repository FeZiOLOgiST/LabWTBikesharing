package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.BikeLogic;
import by.epam.bikesharing.service.pages.PagesLogic;

import javax.servlet.http.HttpServletRequest;

public class ChangeSpotCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        String bikeId = request.getParameter(ParameterName.BIKE_ID);
        String spotId = request.getParameter(ParameterName.SPOT_ID);
        new BikeLogic().editBikeSpot(Long.parseLong(bikeId), spotId);
        request.setAttribute(ParameterName.BIKES, new PagesLogic().searchBikes());
        return ConfigurationManager.getProperty("path.page.bikes");
    }
}