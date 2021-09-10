package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBEmployeeReader;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeListServlet", urlPatterns = "/listOfEmployees")
public class EmployeeListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> listOfEmployees = DBEmployeeReader.getInstance().getListOfEmployee();
        req.setAttribute("listOfEmployees", listOfEmployees);
        req.getRequestDispatcher("/views/listOfEmployees.jsp").forward(req, resp);
    }
}
