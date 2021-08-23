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
import java.util.LinkedList;

@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {

    private static final String SESSION_ATTRIBUTE_PARAM_NAME = "user";
    private static final String TYPE_STORAGE_PARAM_NAME = "typeOfSave";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EStorageType storageType = EStorageType.valueOfIgnoreCase(getServletContext().getInitParameter(TYPE_STORAGE_PARAM_NAME));
        IHandleStorage handler = storageType.getHandler();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SESSION_ATTRIBUTE_PARAM_NAME);
        LinkedList<Text> messages = handler.getMessages(user);
        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/views/chat.jsp").forward(req, resp);
    }
}
