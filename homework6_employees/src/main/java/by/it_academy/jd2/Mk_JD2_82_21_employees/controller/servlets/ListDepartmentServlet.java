package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBDepartmentReader;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListDepartmentServlet", urlPatterns = "/listOfDepartments")
public class ListDepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> listOfDepartments = DBDepartmentReader.getInstance().getListOfDepartments();
        req.setAttribute("listOfDepartments", listOfDepartments);
        req.getRequestDispatcher("/views/listOfDepartments.jsp").forward(req, resp);
    }
}
