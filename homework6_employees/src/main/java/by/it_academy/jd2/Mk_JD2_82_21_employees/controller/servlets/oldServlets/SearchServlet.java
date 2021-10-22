package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets.oldServlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.EmployeeSearchFilter;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPaginationService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/sortingOLD")
public class SearchServlet extends HttpServlet {

    private static final long COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE = 20;
    private static final String SORTED_NUMBER_PAGE_PARAM_NAME = "page";

    private static final String SEARCH_EMPLOYEE_NAME_PARAM_NAME = "searchName";
    private static final String MIN_SEARCH_SALARY_PARAM_NAME = "minSearchSalary";
    private static final String MAX_SEARCH_SALARY_PARAM_NAME = "maxSearchSalary";


    private final IPaginationService paginationService;
    private final IEmployeeService employeeService;

    public SearchServlet(){
        this.paginationService = ApplicationContextUtil.getContext().getBean(IPaginationService.class);
        this.employeeService = ApplicationContextUtil.getContext().getBean(IEmployeeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter(SEARCH_EMPLOYEE_NAME_PARAM_NAME);
        String minSearchSalary = (String) req.getAttribute(MIN_SEARCH_SALARY_PARAM_NAME);
        String maxSearchSalary = (String) req.getAttribute(MAX_SEARCH_SALARY_PARAM_NAME);

        String page;
        if (req.getParameter(SORTED_NUMBER_PAGE_PARAM_NAME) == null) {
            page = "1";
        } else {
            page = req.getParameter(SORTED_NUMBER_PAGE_PARAM_NAME);
        }
        String size = Long.toString(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE);

        EmployeeSearchFilter filter = new EmployeeSearchFilter(page, size, searchName, minSearchSalary, maxSearchSalary);
        req.setAttribute("searchEmployee", filter);

        List<Employee> sortedList = employeeService.getSortedList(filter);
        req.setAttribute("listOfEmployees", sortedList);

        List<Employee> fullListWithoutPages =employeeService.getFullSortedList(filter);
        long countOfRecords = fullListWithoutPages.size();

        long startPosition = paginationService
                .getStartPosition(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, Long.parseLong(page));
        req.setAttribute("startPosition", startPosition);

        long countOfPages = paginationService
                .getCountOfPages(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE,countOfRecords);
        req.setAttribute("countOfPages", countOfPages);

        long[] pages = paginationService.getArrayOfPages(Long.parseLong(page), countOfPages);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher("/views/sortedEmployeePage.jsp").forward(req, resp);
    }
}
