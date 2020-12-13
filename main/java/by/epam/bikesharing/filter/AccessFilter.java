package by.epam.bikesharing.filter;

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

@WebFilter(urlPatterns = { "/jsp/admin/*" }, initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class AccessFilter implements Filter {

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
        if (!ParameterValue.ROLE_ADMIN.equals(user.getRole())) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(httpRequest.getContextPath() + indexPath);
            dispatcher.forward(httpRequest, httpResponse);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() { }
}