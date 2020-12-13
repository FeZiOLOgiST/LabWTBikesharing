package by.epam.bikesharing.command.profile;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalizationCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String locale = request.getParameter(ParameterName.LOCALE);
        if (session != null) {
            session.setAttribute(ParameterName.LOCALE, locale);
            User user = (User) session.getAttribute(ParameterName.USER);
            if (user != null) {
                user.setLocale(locale);
                //todo update db locale
                return ConfigurationManager.getProperty("path.page.profile");
            }
        }
        return ConfigurationManager.getProperty("path.page.login");
    }
}