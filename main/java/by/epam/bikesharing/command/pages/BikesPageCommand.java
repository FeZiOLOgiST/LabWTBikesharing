package by.epam.bikesharing.command.pages;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.pages.BikesPageLogic;

import javax.servlet.http.HttpServletRequest;

public class BikesPageCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        new BikesPageLogic().handleBikesPageRequest(request);
        return ConfigurationManager.getProperty("path.page.bikes");
    }
}