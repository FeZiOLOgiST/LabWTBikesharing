package by.epam.bikesharing.filter;

import by.epam.bikesharing.command.CommandType;
import by.epam.bikesharing.constant.ParameterName;
import by.epam.bikesharing.constant.ParameterValue;
import by.epam.bikesharing.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class ControllerAccessFilter implements Filter {

    private String indexPath;

    public void init(FilterConfig fConfig) {
        indexPath = fConfig.getInitParameter("INDEX_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String command = request.getParameter("command");
        if (command != null) {
            switch (CommandType.valueOf(command.toUpperCase())) {
                case LOGIN:
                case SIGNUP: break;
                case VERIFY:
                case REGISTER: {
                    String role = (String) session.getAttribute(ParameterName.ROLE);
                    if (!ParameterValue.ROLE_GUEST.equals(role)) {
                        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(httpRequest.getContextPath() + indexPath);
                        dispatcher.forward(httpRequest, httpResponse);
                        return;
                    }
                    break;
                }
                case USERS_PAGE:
                case BIKES_PAGE:
                case MODELS_PAGE: {
                    if (!ParameterValue.ROLE_ADMIN.equals(user.getRole())) {
                        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(httpRequest.getContextPath() + indexPath);
                        dispatcher.forward(httpRequest, httpResponse);
                        return;
                    }
                    break;
                }
                default: {
                    if (!ParameterValue.ROLE_ADMIN.equals(user.getRole()) && !ParameterValue.ROLE_USER.equals(user.getRole())) {
                        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(httpRequest.getContextPath() + indexPath);
                        dispatcher.forward(httpRequest, httpResponse);
                        return;
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() { }
}