package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.filters;

import javax.servlet.*;
import java.io.IOException;

public class SearchFilter implements Filter {

    private static final String MIN_SEARCH_SALARY_PARAM_NAME = "minSearchSalary";
    private static final String MAX_SEARCH_SALARY_PARAM_NAME = "maxSearchSalary";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String minSalary = request.getParameter(MIN_SEARCH_SALARY_PARAM_NAME);
        String maxSalary = request.getParameter(MAX_SEARCH_SALARY_PARAM_NAME);

        if (minSalary == null || minSalary.equals("")){
            minSalary = "0";
        }
        if (maxSalary == null || maxSalary.equals("")){
            maxSalary = "999999999.99";
        }
        request.setAttribute(MIN_SEARCH_SALARY_PARAM_NAME, minSalary);
        request.setAttribute(MAX_SEARCH_SALARY_PARAM_NAME, maxSalary);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
