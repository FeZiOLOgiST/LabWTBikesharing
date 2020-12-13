package by.epam.bikesharing.command.balance;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ReplenishPageCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        String cardId = request.getParameter(ParameterName.CARD_ID);
        request.setAttribute(ParameterName.CARD_ID, cardId);
        return ConfigurationManager.getProperty("path.page.replenish");
    }
}