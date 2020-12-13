package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.BikeLogic;
import by.epam.bikesharing.service.pages.BikesPageLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddBikeCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AddBikeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String spot = request.getParameter(ParameterName.SELECT_SPOT);
        String action = request.getParameter(ParameterName.ACTION);
        if (ParameterValue.ACTION_ADD.equals(action)) {
            String serialNumber = request.getParameter(ParameterName.SERIAL_NUMBER);
            String model = request.getParameter(ParameterName.SELECT_MODEL);
            new BikeLogic().addBike(serialNumber, model, spot);
            logger.info(String.format("Bike %s was added", serialNumber));
        } else if (ParameterValue.ACTION_EDIT.equals(action)) {
            String bikeId = request.getParameter(ParameterName.BIKE_ID);
            new BikeLogic().editBikeSpot(Long.parseLong(bikeId), spot);
            logger.info(String.format("Spot of bike %s was changed to %s", bikeId, spot));
        }
        new BikesPageLogic().handleBikesPageRequest(request);
        return ConfigurationManager.getProperty("path.page.bikes");
    }
}