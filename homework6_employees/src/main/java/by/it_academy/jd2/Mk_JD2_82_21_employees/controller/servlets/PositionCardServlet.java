package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.DBDepartmentReader;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.DBPositionReader;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PositionCardServlet", urlPatterns = "/positionCard")
public class PositionCardServlet extends HttpServlet {

    private static final String ID_POSITION_PARAM_NAME = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter(ID_POSITION_PARAM_NAME));
        Position position = DBPositionReader.getInstance().getPosition(id);
        req.setAttribute("position", position);
        req.getRequestDispatcher("/views/positionCard.jsp").forward(req, resp);
    }
}
