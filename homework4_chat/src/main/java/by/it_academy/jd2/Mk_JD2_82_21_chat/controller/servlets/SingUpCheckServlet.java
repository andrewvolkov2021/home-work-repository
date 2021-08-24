package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.SessionService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SingUpCheckServlet", urlPatterns = "/singUpCheck")
public class SingUpCheckServlet extends HttpServlet {

    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_NAME_PARAM_NAME = "password";
    private static final String FIRST_NAME_PARAM_NAME = "firstName";
    private static final String LAST_NAME_PARAM_NAME = "lastName";
    private static final String DATE_PARAM_NAME = "date";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter(LOGIN_PARAM_NAME);
        String password = req.getParameter(PASSWORD_NAME_PARAM_NAME);
        String firstName = req.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = req.getParameter(LAST_NAME_PARAM_NAME);
        String date = req.getParameter(DATE_PARAM_NAME);

        User user = new User(login, password, firstName, lastName, date);

        StorageService.getInstance().saveNewUser(user);
        SessionService.getInstance().save(req, user);

        req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
    }
}
