package by.epam.bikesharing.command.signup;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.LocaleConstant;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.service.PasswordHash;
import by.epam.bikesharing.service.email.EmailSender;
import by.epam.bikesharing.service.email.VerificationCode;
import by.epam.bikesharing.validation.AuthenticatorValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        String email = request.getParameter(ParameterName.EMAIL);
        StringBuilder messageBuilder = new StringBuilder();
        if (!AuthenticatorValidation.isValidEmail(email)) {
            messageBuilder.append("<br>").append(MessageManager.getProperty("message.invalid_email"));
        }
        if (AuthenticatorValidation.emailIsInUse(email)) {
            messageBuilder.append("<br>").append(MessageManager.getProperty("message.email_in_use"));
        }
        if (!AuthenticatorValidation.isValidLogin(login)) {
            messageBuilder.append("<br>").append(MessageManager.getProperty("message.invalid_login"));
        }
        if (AuthenticatorValidation.loginIsInUse(login)) {
            messageBuilder.append("<br>").append(MessageManager.getProperty("message.login_in_use"));
        }
        if (!AuthenticatorValidation.isValidPassword(password)) {
            messageBuilder.append("<br>").append(MessageManager.getProperty("message.invalid_password"));
        }
        String message = messageBuilder.toString();
        if (!message.isEmpty()) {
            request.setAttribute(ParameterName.MESSAGE, message);
            logger.info("Invalid registration data was detected.");
            return ConfigurationManager.getProperty("path.page.signup");
        }
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(ParameterName.ROLE).equals(ParameterValue.ROLE_GUEST)) {
            String verificationCode = (new VerificationCode()).getCode(6);
            PasswordHash passwordHash = new PasswordHash(password);
            session.setAttribute(ParameterName.LOGIN, login);
            session.setAttribute(ParameterName.EMAIL, email);
            session.setAttribute(ParameterName.HASH, passwordHash);
            session.setAttribute(ParameterName.CODE, verificationCode);
            EmailSender emailSender = new EmailSender(email);
            emailSender.sendVerificationCode(verificationCode);
            page = ConfigurationManager.getProperty("path.page.verification");
            logger.info(String.format("Registration step complete. Next sending code to %s", email));
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror", LocaleConstant.DEFAULT));
            page = ConfigurationManager.getProperty("path.page.login");
            logger.error("Session error trying register user.");
        }
        return page;
    }
}