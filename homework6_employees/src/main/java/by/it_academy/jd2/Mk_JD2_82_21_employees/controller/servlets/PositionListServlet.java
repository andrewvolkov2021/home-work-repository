package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBPositionReader;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PositionListServlet", urlPatterns = "/listOfPositions")
public class PositionListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Position> listOfPositions = DBPositionReader.getInstance().getListOfPosition();
        req.setAttribute("listOfPositions", listOfPositions);
        req.getRequestDispatcher("/views/listOfPositions.jsp").forward(req, resp);
    }
}
