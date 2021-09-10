package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddEmployeeServlet", urlPatterns = "/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    private static final String NAME_EMPLOYEE_PARAM_NAME = "name";
    private static final String SALARY_PARAM_NAME = "salary";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_EMPLOYEE_PARAM_NAME);
        double salary = Double.parseDouble(req.getParameter(SALARY_PARAM_NAME));

        long id = DBInitializer.getInstance().addEmployee(name, salary);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/views/newID.jsp").forward(req, resp);
    }
}
