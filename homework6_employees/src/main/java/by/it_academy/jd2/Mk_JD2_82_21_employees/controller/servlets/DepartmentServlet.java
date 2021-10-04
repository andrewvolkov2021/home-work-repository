package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.NewDepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {

    private static final String ID_DEPARTMENT_PARAM_NAME = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(ID_DEPARTMENT_PARAM_NAME) == null){
            List<Department> listOfDepartments = NewDepartmentService.getInstance().getListOfDepartments();
            req.setAttribute("listOfDepartments", listOfDepartments);
            req.getRequestDispatcher("/views/listOfDepartments.jsp").forward(req, resp);
        } else {
            long id = Long.parseLong(req.getParameter(ID_DEPARTMENT_PARAM_NAME));
            Department department = NewDepartmentService.getInstance().getDepartment(id);
            req.setAttribute("department", department);
            req.getRequestDispatcher("/views/departmentCard.jsp").forward(req, resp);
        }
    }
}
