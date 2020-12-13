package by.epam.bikesharing.command.admin;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.BikeModelLogic;
import by.epam.bikesharing.service.pages.PagesLogic;

import javax.servlet.http.HttpServletRequest;

public class ChangeCostCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(ParameterName.ID);
        String cost = request.getParameter(ParameterName.COST);
        new BikeModelLogic().editModelCost(Long.parseLong(id), cost);
        request.setAttribute(ParameterName.MODELS, new PagesLogic().searchModels());
        return ConfigurationManager.getProperty("path.page.models");
    }
}