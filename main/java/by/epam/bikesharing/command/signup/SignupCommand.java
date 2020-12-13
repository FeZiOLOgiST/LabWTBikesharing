package by.epam.bikesharing.command.signup;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignupCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        session = request.getSession(true);
        session.setAttribute(ParameterName.ROLE, ParameterValue.ROLE_GUEST);
        return ConfigurationManager.getProperty("path.page.signup");
    }
}