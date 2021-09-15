package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.DepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DepartmentCardServlet", urlPatterns = "/departmentCard")
public class DepartmentCardServlet extends HttpServlet {

    private static final String ID_DEPARTMENT_PARAM_NAME = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(ID_DEPARTMENT_PARAM_NAME));
        Department department = DepartmentService.getInstance().getDepartment(id);
        req.setAttribute("department", department);
        req.getRequestDispatcher("/views/departmentCard.jsp").forward(req, resp);
    }
}
