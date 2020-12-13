package by.epam.bikesharing.command.profile;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.service.pages.PagesLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements ActionCommand {

    private static final String PROFILE_LOGIN = "profile_name";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String login = request.getParameter(PROFILE_LOGIN);
        User userProfile = new PagesLogic().getUserProfile(user, login);
        request.setAttribute(ParameterName.PROFILE_USER, userProfile);
        return ConfigurationManager.getProperty("path.page.profile");
    }
}