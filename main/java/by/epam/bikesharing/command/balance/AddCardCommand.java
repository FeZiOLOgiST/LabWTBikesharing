package by.epam.bikesharing.command.balance;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ServiceConstant;
import by.epam.bikesharing.entity.Card;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.service.CardLogic;
import by.epam.bikesharing.service.pages.PagesLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddCardCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(AddCardCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String number = request.getParameter(ParameterName.NUMBER);
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String month = request.getParameter(ParameterName.MONTH);
        String year = request.getParameter(ParameterName.YEAR);
        String cvv = request.getParameter(ParameterName.CVV);
        String addCardResult = new CardLogic().addCard(user.getId(), number, firstName, lastName, month, year, cvv);
        if (ServiceConstant.UPDATE_SUCCESS.equals(addCardResult)) {
            List<Card> cards = PagesLogic.getUserCards(user.getId());
            request.setAttribute(ParameterName.CARDS, cards);
            page = ConfigurationManager.getProperty("path.page.cards");
            logger.info("Card was added successfully for user: " + user.toString());
        } else {
            request.setAttribute(ParameterName.MESSAGE, MessageManager.getProperty(addCardResult, user.getLocale()));
            page = ConfigurationManager.getProperty("path.page.add_card");
            logger.error("Invalid card data was entered or error during updating cards db: " + user.toString());
        }
        return page;
    }
}