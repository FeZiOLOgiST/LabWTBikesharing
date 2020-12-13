package by.epam.bikesharing.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {


    String execute(HttpServletRequest request);
}