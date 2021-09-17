package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.SelectEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeesPageServlet", urlPatterns = "/employeesPage")
public class EmployeesPageServlet extends HttpServlet {

    private static final long COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE = 20;
    private static final String NUMBER_PAGE_PARAM_NAME = "page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Вынести проверку в фильтр
        long page = 0;
        if (req.getParameter(NUMBER_PAGE_PARAM_NAME) == null){
            page = 1;
        } else {
            page = Long.parseLong(req.getParameter(NUMBER_PAGE_PARAM_NAME));
        }

        List<Employee> listOfEmployees = SelectEmployeeService.getInstance().getSelectListOfEmployee(
                COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, page);
        req.setAttribute("listOfEmployees", listOfEmployees);

        long countOfPages = SelectEmployeeService.getInstance().getCountOfPages(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE);
        req.setAttribute("countOfPages", countOfPages);

        long[] pages = SelectEmployeeService.getInstance().getArrayOfPages(page, countOfPages);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher("/views/employeesPage.jsp").forward(req, resp);
    }
}
