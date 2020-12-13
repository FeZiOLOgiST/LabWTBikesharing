package by.epam.bikesharing;

import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.command.ActionFactory;
import by.epam.bikesharing.constant.LocaleConstant;
import by.epam.bikesharing.resource.ConfigurationManager;
import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.resource.StringManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")

public class Controller extends HttpServlet {

    public void init() throws ServletException {
        getServletContext().setAttribute("stringManager", StringManager.getInstance());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        SessionRequestContent content = new SessionRequestContent();
        content.extractValues(request);
        ActionCommand command = ActionFactory.defineCommand(content);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage", LocaleConstant.DEFAULT));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}