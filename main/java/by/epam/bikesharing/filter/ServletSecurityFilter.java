package by.epam.bikesharing.filter;

import by.epam.bikesharing.constant.ParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter( urlPatterns = { "/jsp/*" }, initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class ServletSecurityFilter implements Filter {

    private String indexPath;

    public void init(FilterConfig fConfig) {
        indexPath = fConfig.getInitParameter("INDEX_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute(ParameterName.USER) == null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(httpRequest.getContextPath() + indexPath);
            dispatcher.forward(httpRequest, httpResponse);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() { }
}