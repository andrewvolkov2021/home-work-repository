package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.SelectEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.SearchEmployee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/sorting")
public class SearchServlet extends HttpServlet {

    private static final long COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE = 20;
    private static final String SORTED_NUMBER_PAGE_PARAM_NAME = "page";

    private static final String SEARCH_EMPLOYEE_NAME_PARAM_NAME = "searchName";
    private static final String MIN_SEARCH_SALARY_PARAM_NAME = "minSearchSalary";
    private static final String MAX_SEARCH_SALARY_PARAM_NAME = "maxSearchSalary";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = (String) req.getAttribute(SEARCH_EMPLOYEE_NAME_PARAM_NAME);
        double minSearchSalary = Double.parseDouble((String) req.getAttribute(MIN_SEARCH_SALARY_PARAM_NAME));
        double maxSearchSalary = Double.parseDouble((String) req.getAttribute(MAX_SEARCH_SALARY_PARAM_NAME));
        SearchEmployee searchEmployee = new SearchEmployee(searchName, minSearchSalary, maxSearchSalary);
        req.setAttribute("searchEmployee", searchEmployee);

        long page = 0;
        if (req.getParameter(SORTED_NUMBER_PAGE_PARAM_NAME) == null) {
            page = 1;
        } else {
            page = Long.parseLong(req.getParameter(SORTED_NUMBER_PAGE_PARAM_NAME));
        }

        long startPosition = (page - 1) * COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE + 1;
        req.setAttribute("startPosition", startPosition);

        List<Employee> fullListOfEmployees = SelectEmployeeService.getInstance()
                .getFullList(searchEmployee);
        List<Employee> listOfEmployees = SelectEmployeeService.getInstance()
                .getSortedList(page, COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, fullListOfEmployees);
        req.setAttribute("listOfEmployees", listOfEmployees);

        long countOfPages = SelectEmployeeService.getInstance().
                getCountOfSortedPages(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE,fullListOfEmployees);
        req.setAttribute("countOfPages", countOfPages);

        long[] pages = SelectEmployeeService.getInstance().getArrayOfPages(page, countOfPages);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher("/views/sortedEmployeePage.jsp").forward(req, resp);
    }
}
