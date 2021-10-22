package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employeeOLD")
public class EmployeeServlet extends HttpServlet {

    private static final String NAME_EMPLOYEE_PARAM_NAME = "name";
    private static final String SALARY_PARAM_NAME = "salary";

    private final IEmployeeService employeeService;

    public EmployeeServlet(){
        this.employeeService = ApplicationContextUtil.getContext().getBean(IEmployeeService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Добавление нового сотрудника вручную
        String name = req.getParameter(NAME_EMPLOYEE_PARAM_NAME);
        double salary = Double.parseDouble(req.getParameter(SALARY_PARAM_NAME));

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);

        long id = employeeService.addEmployee(employee);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/views/newID.jsp").forward(req, resp);
    }
}
