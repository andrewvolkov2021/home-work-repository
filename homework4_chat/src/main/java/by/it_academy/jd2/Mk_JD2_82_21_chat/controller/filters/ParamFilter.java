package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.filters;

import javax.servlet.*;
import java.io.IOException;

public class ParamFilter implements Filter {

    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_NAME_PARAM_NAME = "password";
    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String DATE_PARAM_NAME = "date";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String login = servletRequest.getParameter(LOGIN_PARAM_NAME);
        String password = servletRequest.getParameter(PASSWORD_NAME_PARAM_NAME);
        String firstName = servletRequest.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = servletRequest.getParameter(LAST_NAME_PARAM_NAME);
        String date = servletRequest.getParameter(DATE_PARAM_NAME);

        if (login == "" || password == "" || firstName == "" || lastName =="" || date == ""
                ||login == null || password == null || firstName == null || lastName == null || date == null ) {
            servletRequest.getRequestDispatcher("/views/singUp_param_exception.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
