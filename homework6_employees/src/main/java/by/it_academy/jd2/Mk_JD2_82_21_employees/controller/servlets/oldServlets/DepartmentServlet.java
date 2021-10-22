package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets.oldServlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "DepartmentServlet", urlPatterns = "/departmentOLD")
public class DepartmentServlet extends HttpServlet {

    private static final String ID_DEPARTMENT_PARAM_NAME = "id";
    private final IDepartmentService departmentService;

    public DepartmentServlet(){
        this.departmentService = ApplicationContextUtil.getContext().getBean(IDepartmentService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(ID_DEPARTMENT_PARAM_NAME) == null){
            List<Department> listOfDepartments = departmentService.getListOfDepartments();
            req.setAttribute("listOfDepartments", listOfDepartments);
            req.getRequestDispatcher("/views/listOfDepartments.jsp").forward(req, resp);
        } else {
            long id = Long.parseLong(req.getParameter(ID_DEPARTMENT_PARAM_NAME));
            Department department = departmentService.getDepartment(id);
            req.setAttribute("department", department);
            req.getRequestDispatcher("/views/departmentCard.jsp").forward(req, resp);
        }
    }
}
