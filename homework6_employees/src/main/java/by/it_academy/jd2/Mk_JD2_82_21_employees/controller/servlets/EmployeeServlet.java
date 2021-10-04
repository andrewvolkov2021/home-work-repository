package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.NewEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.NewPaginationService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {

    private static final long COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE = 20;
    private static final String NUMBER_PAGE_PARAM_NAME = "page";
    private static final String EMPLOYEE_PARAM_NAME = "paramEmployee";
    private static final String ID_EMPLOYEE_PARAM_NAME = "id";
    private static final String NAME_EMPLOYEE_PARAM_NAME = "name";
    private static final String SALARY_PARAM_NAME = "salary";
    private static final String EMPLOYEE_CARD_PARAM_NAME = "getId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Постраничный вывод списка сотрудников
        if (req.getParameter(EMPLOYEE_PARAM_NAME) != null) {

            long page = 0;
            if (req.getParameter(NUMBER_PAGE_PARAM_NAME) == null) {
                page = 1;
            } else {
                page = Long.parseLong(req.getParameter(NUMBER_PAGE_PARAM_NAME));
            }

            long startPosition = (page - 1) * COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE + 1;
            req.setAttribute("startPosition", startPosition);

            List<Employee> listOfEmployees =NewEmployeeService.getInstance().getListOfEmployees(
                    COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, page);
            req.setAttribute("listOfEmployees", listOfEmployees);

            long countOfPages = NewPaginationService.getInstance().getCountOfPages(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE);
            req.setAttribute("countOfPages", countOfPages);

            long[] pages = NewPaginationService.getInstance().getArrayOfPages(page, countOfPages);
            req.setAttribute("pages", pages);

            req.getRequestDispatcher("/views/employeesPage.jsp").forward(req, resp);
        }

        //Получение карточки сотрудника по ID
        if (req.getParameter(ID_EMPLOYEE_PARAM_NAME) != null) {
            long id = Long.parseLong(req.getParameter(ID_EMPLOYEE_PARAM_NAME));
            Employee employee = NewEmployeeService.getInstance().getEmployee(id);
            req.setAttribute("employee", employee);
            req.getRequestDispatcher("/views/newEmployeeCard.jsp").forward(req, resp);
        }

        //Перенаправление на сервлет для получения карточки по ID
        if (req.getParameter(EMPLOYEE_CARD_PARAM_NAME) != null) {
            req.getRequestDispatcher("/views/getCard.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/views/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Добавление нового сотрудника вручную
        String name = req.getParameter(NAME_EMPLOYEE_PARAM_NAME);
        double salary = Double.parseDouble(req.getParameter(SALARY_PARAM_NAME));

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);

        long id = NewEmployeeService.getInstance().addEmployee(employee);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/views/newID.jsp").forward(req, resp);
    }
}
