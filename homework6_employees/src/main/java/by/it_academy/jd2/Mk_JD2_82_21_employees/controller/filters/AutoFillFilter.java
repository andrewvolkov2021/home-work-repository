package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.filters;

import javax.servlet.*;
import java.io.IOException;

public class AutoFillFilter implements Filter {

    private static final String EMPLOYEE_COUNT_PARAM_NAME = "count";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String count = request.getParameter(EMPLOYEE_COUNT_PARAM_NAME);
        if (count == null || count.equals("")) {
            request.getRequestDispatcher("/views/autoFill_exception.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
