package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.filters;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleStorage;

import javax.servlet.*;
import java.io.IOException;

public class RecipientFilter implements Filter {

    private static final String LOGIN_OF_RECIPIENT_PARAM_NAME = "loginRecipient";
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

        String login = servletRequest.getParameter(LOGIN_OF_RECIPIENT_PARAM_NAME);
        if(handler.getUser(login) == null) {
            servletRequest.getRequestDispatcher("/views/message_exception.jsp").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
