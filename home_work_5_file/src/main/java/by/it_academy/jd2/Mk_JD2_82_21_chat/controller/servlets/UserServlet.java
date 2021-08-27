package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleInfo;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private static final String TYPE_STORAGE_PARAM_NAME = "typeOfSave";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EStorageType storageType = EStorageType.valueOfIgnoreCase(getServletContext().getInitParameter(TYPE_STORAGE_PARAM_NAME));
        IHandleInfo handleInfo = storageType.getHandleInfo();

        Set<User> set = handleInfo.getAllUsers();
        req.setAttribute("set", set);

        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }
}
