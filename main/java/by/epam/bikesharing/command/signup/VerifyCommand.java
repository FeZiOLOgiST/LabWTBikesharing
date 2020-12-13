package by.epam.bikesharing.command.signup;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.service.PasswordHash;
import by.epam.bikesharing.service.signup.SignupLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerifyCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(VerifyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String enteredCode = request.getParameter(ParameterName.CODE);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(ParameterName.ROLE).equals(ParameterValue.ROLE_GUEST)) {
            String sendedCode = (String) session.getAttribute(ParameterName.CODE);
            if (enteredCode.equals(sendedCode)) {
                String userLogin = (String) session.getAttribute(ParameterName.LOGIN);
                String userEmail = (String) session.getAttribute(ParameterName.EMAIL);
                PasswordHash userHash = (PasswordHash) session.getAttribute(ParameterName.HASH);
                SignupLogic logic = new SignupLogic();
                logic.addUser(userLogin, userEmail, userHash);
                page = ConfigurationManager.getProperty("path.page.index");
            } else {
                page = ConfigurationManager.getProperty("path.page.verification");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
            logger.error("Session error trying verify user.");
        }
        return page;
    }
}