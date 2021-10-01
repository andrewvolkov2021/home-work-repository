package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.filters;

import javax.servlet.*;
import java.io.IOException;

public class SortingFilter implements Filter {

    private static final String SEARCH_NAME_PARAM_NAME = "searchName";
    private static final String MIN_SEARCH_SALARY_PARAM_NAME = "minSearchSalary";
    private static final String MAX_SEARCH_SALARY_PARAM_NAME = "maxSearchSalary";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = request.getParameter(SEARCH_NAME_PARAM_NAME);
        if (name == null){
            name = "";
        }
        request.setAttribute(SEARCH_NAME_PARAM_NAME, name);

        String minSalary = request.getParameter(MIN_SEARCH_SALARY_PARAM_NAME);
        if (minSalary == null || minSalary.equals("")){
            minSalary = "0";
        }
        request.setAttribute(MIN_SEARCH_SALARY_PARAM_NAME, minSalary);

        String maxSalary = request.getParameter(MAX_SEARCH_SALARY_PARAM_NAME);
        if (maxSalary == null || maxSalary.equals("")){
            maxSalary = "99999999.99";
        }
        request.setAttribute(MAX_SEARCH_SALARY_PARAM_NAME, maxSalary);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
