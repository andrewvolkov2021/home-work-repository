package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.DBInitializer;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeCardServlet", urlPatterns = "/employeeCard")
public class EmployeeCardServlet extends HttpServlet {

    private static final String ID_PARAM_NAME = "id_employee";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(ID_PARAM_NAME));

        Employee employee = DBInitializer.getInstance().getEmployee(id);
        req.setAttribute("employee", employee);
        req.getRequestDispatcher("/views/employeeCard.jsp").forward(req, resp);
    }
}