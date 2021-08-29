package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleStorage;
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
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet(name = "MessageCheckServlet", urlPatterns = "/messageCheck")
public class MessageCheckServlet extends HttpServlet {

    private static final String TEXT_PARAM_NAME = "text";
    private static final String SESSION_ATTRIBUTE_PARAM_NAME = "user";
    private static final String LOGIN_OF_RECIPIENT_PARAM_NAME = "loginRecipient";

    private static final String TYPE_STORAGE_PARAM_NAME = "typeOfSave";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EStorageType storageType = EStorageType.valueOfIgnoreCase(getServletContext().getInitParameter(TYPE_STORAGE_PARAM_NAME));
        IHandleStorage handler = storageType.getHandler();

        HttpSession session = req.getSession();
        User sender = (User) session.getAttribute(SESSION_ATTRIBUTE_PARAM_NAME);

        String loginRecipient = req.getParameter(LOGIN_OF_RECIPIENT_PARAM_NAME);
        User recipient = handler.getUser(loginRecipient);

        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM, dd, yyyy HH:mm:ss", Locale.US);
        String date = localDate.format(formatter);

        String message = req.getParameter(TEXT_PARAM_NAME);

        Text text = new Text(message, sender, date);

        handler.setMessage(recipient, text);

        resp.sendRedirect("/Mk-JD2-82-21-chat-0.0.0-SNAPSHOT/message");
    }
}
