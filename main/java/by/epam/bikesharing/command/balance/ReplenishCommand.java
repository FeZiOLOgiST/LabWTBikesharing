package by.epam.bikesharing.command.balance;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.entity.User;
import by.epam.bikesharing.exception.NotEnoughMoneyException;
import by.epam.bikesharing.exception.TransactionException;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.service.BalanceLogic;
import by.epam.bikesharing.validation.BankAccountValidation;
import by.epam.bikesharing.validation.MoneyValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ReplenishCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger(ReplenishCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        long userId = user.getId();
        long cardId = Long.parseLong(request.getParameter(ParameterName.CARD_ID));
        String amountString = request.getParameter(ParameterName.AMOUNT);
        if (!MoneyValidation.isValid(amountString)) {
            session.setAttribute("message", MessageManager.getProperty("message.amount", user.getLocale()));
            return ConfigurationManager.getProperty("path.page.add_card");
        }
        BigDecimal amount = new BigDecimal(amountString);
        if (!MoneyValidation.isNotZero(amount)) {
            session.setAttribute("message", MessageManager.getProperty("message.amount", user.getLocale()));
            return ConfigurationManager.getProperty("path.page.add_card");
        }
        String cvv = request.getParameter("cvv");
        if (!BankAccountValidation.isValidCvv(cvv)) {
            session.setAttribute("message", MessageManager.getProperty("message.invalid_cvv", user.getLocale()));
            return ConfigurationManager.getProperty("path.page.add_card");
        }
        try {
            session.setAttribute(ParameterName.USER, new BalanceLogic().replenish(userId, cardId, amount));
            logger.info("Successful balance transaction for user " + user.toString());
        } catch (NotEnoughMoneyException e) {
            logger.info("Not enough money on card of user" + user.toString());
        } catch (TransactionException e) {
            logger.error("Balance transaction wasn't successful for user" + user.toString());
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}