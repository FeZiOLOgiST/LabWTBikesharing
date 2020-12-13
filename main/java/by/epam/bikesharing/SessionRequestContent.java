package by.epam.bikesharing;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {

    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public SessionRequestContent() {
        this.requestAttributes = new HashMap<>();
        this.requestParameters = new HashMap<>();
        this.sessionAttributes = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) {
        extractRequestAttributes(request);
        extractRequestParameters(request);
        extractSessionAttributes(request);
    }

    public void insertAttributes(HttpServletRequest request) {
        for (Map.Entry<String, Object> attribute : requestAttributes.entrySet()) {
            request.setAttribute(attribute.getKey(), attribute.getValue());
        }
        for (Map.Entry<String, Object> attribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    public void setAttribute(String key, String value) {
        requestAttributes.put(key, value);
    }

    private void extractRequestAttributes(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            requestAttributes.put(attributeName, request.getAttribute(attributeName));
        }
    }

    private void extractRequestParameters(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            requestParameters.put(parameterName, request.getParameterValues(parameterName));
        }
    }

    private void extractSessionAttributes(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            sessionAttributes.put(attributeName, request.getSession().getAttribute(attributeName));
        }
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }
}