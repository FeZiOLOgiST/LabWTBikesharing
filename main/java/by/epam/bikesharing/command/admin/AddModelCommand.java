package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.BikeModelLogic;
import by.epam.bikesharing.service.pages.PagesLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddModelCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AddModelCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String cost = request.getParameter(ParameterName.COST);
        String action = request.getParameter(ParameterName.ACTION);
        if (ParameterValue.ACTION_ADD.equals(action)) {
            String name = request.getParameter(ParameterName.NAME);
            String image = request.getParameter(ParameterName.IMAGE);
            new BikeModelLogic().addModel(name, cost, image);
            logger.info(String.format("Model %s was added", name));
        } else if (ParameterValue.ACTION_EDIT.equals(action)) {
            String id = request.getParameter(ParameterName.ID);
            new BikeModelLogic().editModelCost(Long.parseLong(id), cost);
            logger.info(String.format("Cost of model %s was changed to %s", id, cost));
        }
        request.setAttribute(ParameterName.MODELS, new PagesLogic().searchModels());
        return ConfigurationManager.getProperty("path.page.models");
    }
}