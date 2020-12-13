package by.epam.bikesharing.command.profile;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.constant.ServiceConstant;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.service.ProfileLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SaveProfileCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(SaveProfileCommand.class);
    private static final String OLD_PASSWORD = "old_password";
    private static final String NEW_PASSWORD = "new_password";
    private static final String REPEAT_PASSWORD = "repeat_password";
    private static final String UPDATE_IMAGE_FLAG = "update_image";
    
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        ProfileLogic logic = setupLogic(request, user);
        String message = logic.updateProfile();
        if (ServiceConstant.UPDATE_SUCCESS.equals(message)) {
            user = logic.getNewUser();
            session.setAttribute(ParameterName.USER, user);
            logger.info(String.format("Profile data was saved for user %s", user));
        } else {
            request.setAttribute(ParameterName.MESSAGE, MessageManager.getProperty(message, (String) session.getAttribute("locale")));
        }
        request.setAttribute(ParameterName.PROFILE_USER, user);
        return ConfigurationManager.getProperty("path.page.profile");
    }

    private ProfileLogic setupLogic(HttpServletRequest request, User user) {
        ProfileLogic logic = new ProfileLogic(user);
        logic.setLogin(request.getParameter(ParameterName.LOGIN));
        logic.setEmail(request.getParameter(ParameterName.EMAIL));
        logic.setOldPassword(request.getParameter(OLD_PASSWORD));
        logic.setNewPassword(request.getParameter(NEW_PASSWORD));
        logic.setRepeatPassword(request.getParameter(REPEAT_PASSWORD));
        if (ParameterValue.TRUE.equals(request.getParameter(UPDATE_IMAGE_FLAG))) {
            logic.setImage(request.getParameter(ParameterName.IMAGE));
        }
        return logic;
    }
}