package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.Text;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    private static final String TEXT_PARAM_NAME = "text";
    private static final String SESSION_ATTRIBUTE_PARAM_NAME = "user";
    private static final String LOGIN_OF_RECIPIENT_PARAM_NAME = "loginRecipient";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        while (true) {
            req.getRequestDispatcher("/views/message.jsp").forward(req, resp);

            HttpSession session = req.getSession();
            User sender = (User) session.getAttribute(SESSION_ATTRIBUTE_PARAM_NAME);

            String loginRecipient = req.getParameter(LOGIN_OF_RECIPIENT_PARAM_NAME);
            User recipient = StorageService.getInstance().getUser(loginRecipient);

            LocalDateTime date = LocalDateTime.now();
            String message = req.getParameter(TEXT_PARAM_NAME);

            Text text = new Text(message, sender, date);

            StorageService.getInstance().setMessage(recipient, text);
        }
    }
}
