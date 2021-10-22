package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets.oldServlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPositionService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PositionServlet", urlPatterns = "/positionOLD")
public class PositionServlet extends HttpServlet {

    private static final String ID_POSITION_PARAM_NAME = "id";

    private final IPositionService positionService;

    public PositionServlet(){
        this.positionService = ApplicationContextUtil.getContext().getBean(IPositionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(ID_POSITION_PARAM_NAME) == null) {
            List<Position> listOfPositions = positionService.getListOfPositions();
            req.setAttribute("listOfPositions", listOfPositions);
            req.getRequestDispatcher("/views/listOfPositions.jsp").forward(req, resp);
        } else {
            long id = Long.parseLong(req.getParameter(ID_POSITION_PARAM_NAME));
            Position position = positionService.getPosition(id);
            req.setAttribute("position", position);
            req.getRequestDispatcher("/views/positionCard.jsp").forward(req, resp);
        }
    }
}
