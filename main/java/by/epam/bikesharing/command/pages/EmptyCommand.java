package by.epam.bikesharing.command.pages;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.login");
    }
}
