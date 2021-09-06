package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.filters;

import javax.servlet.*;
import java.io.IOException;

public class EmployeeParamFilter implements Filter {

    private static final String NAME_EMPLOYEE_PARAM_NAME = "name";
    private static final String SALARY_PARAM_NAME = "salary";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = request.getParameter(NAME_EMPLOYEE_PARAM_NAME);
        String salary = request.getParameter(SALARY_PARAM_NAME);

        if (name.equals("") || salary.equals("")){
            request.getRequestDispatcher("/views/start_exception.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
