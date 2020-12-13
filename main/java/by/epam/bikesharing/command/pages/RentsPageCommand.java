package by.epam.bikesharing.command.pages;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.Rent;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.pages.PagesLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RentsPageCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        long id = Long.parseLong(request.getParameter(ParameterName.ID));
        List<Rent> rents = new PagesLogic().getUserRents(user, id);
        request.setAttribute(ParameterName.RENTS, rents);
        return ConfigurationManager.getProperty("path.page.rents");
    }
}