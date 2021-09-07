package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.filters;

import javax.servlet.*;
import java.io.IOException;

public class IDFilter implements Filter {

    private static final String ID_PARAM_NAME = "id_employee";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String id = request.getParameter(ID_PARAM_NAME);
        if (id == null || id.equals("")) {
            request.getRequestDispatcher("/views/getCard_exception.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
