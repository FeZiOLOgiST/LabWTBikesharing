package by.epam.bikesharing.command.rent;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.TransactionException;
import by.epam.bikesharing.service.pages.MainRentPageLogic;
import by.epam.bikesharing.service.rent.RentFinishedTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FinishRentCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(FinishRentCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            user = new RentFinishedTransaction().finishRent(user.getId(), user.getBikeId(), 8);
            session.setAttribute("user", user);
        } catch (TransactionException e) {
            logger.error(String.format("Error trying finish current ride of user %s", user));
        }
        page = new MainRentPageLogic().getUserMainPage(user, request);
        return page;
    }
}