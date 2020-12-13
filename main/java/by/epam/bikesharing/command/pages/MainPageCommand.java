package by.epam.bikesharing.command.pages;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.service.pages.MainRentPageLogic;

import javax.servlet.http.HttpServletRequest;

public class MainPageCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(ParameterName.USER);
        return new MainRentPageLogic().getUserMainPage(user, request);
    }
}