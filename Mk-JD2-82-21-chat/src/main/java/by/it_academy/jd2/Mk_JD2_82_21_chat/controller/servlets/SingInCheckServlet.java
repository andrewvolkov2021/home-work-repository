package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SingInCheckServlet", urlPatterns = "/singInCheck")
public class SingInCheckServlet extends HttpServlet {

    private static final String LOGIN_PARAM_NAME = "login";
    private static final String SESSION_ATTRIBUTE_PARAM_NAME = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String login = req.getParameter(LOGIN_PARAM_NAME);
        User user = StorageService.getInstance().getUser(login);
        session.setAttribute(SESSION_ATTRIBUTE_PARAM_NAME, user);
        resp.sendRedirect("http://localhost:8080/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/message");
    }
}
