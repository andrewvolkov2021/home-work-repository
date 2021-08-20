package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.filters;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String LOGIN_PARAM_NAME = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String login = servletRequest.getParameter(LOGIN_PARAM_NAME);
        if (StorageService.getInstance().getUser(login) != null) {
            servletRequest.getRequestDispatcher("/views/singUp_login_exception.jsp").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
