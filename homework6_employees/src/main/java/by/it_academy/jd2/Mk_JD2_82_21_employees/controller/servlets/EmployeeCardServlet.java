package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBEmployeeReader;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeCardServlet", urlPatterns = "/employeeCard")
public class EmployeeCardServlet extends HttpServlet {

    private static final String ID_EMPLOYEE_PARAM_NAME = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(ID_EMPLOYEE_PARAM_NAME));
        Employee employee = DBEmployeeReader.getInstance().getEmployee(id);
        req.setAttribute("employee", employee);
        req.getRequestDispatcher("/views/newEmployeeCard.jsp").forward(req, resp);
    }
}
