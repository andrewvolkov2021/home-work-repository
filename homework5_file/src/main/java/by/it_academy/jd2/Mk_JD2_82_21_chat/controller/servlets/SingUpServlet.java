package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SingUpServlet", urlPatterns = "/singUp")
public class SingUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/views/singUp.jsp").forward(req, resp);
        resp.sendRedirect("/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/singUpCheck");
    }
}
