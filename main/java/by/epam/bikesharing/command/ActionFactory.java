package by.epam.bikesharing.command;

import by.epam.bikesharing.SessionRequestContent;
import by.epam.bikesharing.command.pages.EmptyCommand;
import by.epam.bikesharing.constant.LocaleConstant;
import by.epam.bikesharing.resource.MessageManager;

public class ActionFactory {

    public static ActionCommand defineCommand(SessionRequestContent request) {

        ActionCommand current = new EmptyCommand();
        String action = request.getRequestParameters().get("command")[0];
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction", LocaleConstant.DEFAULT));
        }
        return current;
    }
}