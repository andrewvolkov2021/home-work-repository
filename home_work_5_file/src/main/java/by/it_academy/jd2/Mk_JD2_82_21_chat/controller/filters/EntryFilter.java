package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.filters;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleStorage;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import javax.servlet.*;
import java.io.IOException;

public class EntryFilter implements Filter {

    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String TYPE_STORAGE_PARAM_NAME = "typeOfSave";

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        EStorageType storageType = EStorageType.valueOfIgnoreCase(filterConfig.getServletContext().getInitParameter(TYPE_STORAGE_PARAM_NAME));
        IHandleStorage handler = storageType.getHandler();

        String login = servletRequest.getParameter(LOGIN_PARAM_NAME);
        String password = servletRequest.getParameter(PASSWORD_PARAM_NAME);

        User user = handler.getUser(login);
        if (user == null) {
            servletRequest.getRequestDispatcher("/views/singIn_exception.jsp").forward(servletRequest, servletResponse);
        } else {
            String userPassword = user.getPassword();
            if (!password.equals(userPassword)) {
                servletRequest.getRequestDispatcher("/views/singIn_exception.jsp").forward(servletRequest, servletResponse);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
