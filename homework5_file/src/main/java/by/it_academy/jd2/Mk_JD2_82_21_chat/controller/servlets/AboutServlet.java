package by.it_academy.jd2.Mk_JD2_82_21_chat.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet(name = "AboutServlet", urlPatterns = "/about")
public class AboutServlet extends HttpServlet {
    private static String START_TIME_PARAM_NAME = "time";
    private static final String TYPE_STORAGE_PARAM_NAME = "typeOfSave";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime dateTime = (LocalDateTime) req.getServletContext().getAttribute(START_TIME_PARAM_NAME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM, dd, yyyy HH:mm:ss", Locale.US);
        String time = dateTime.format(formatter);
        req.setAttribute("time", time);

        String typeOfStorage = (String) req.getServletContext().getInitParameter(TYPE_STORAGE_PARAM_NAME);
        req.setAttribute("typeOfStorage", typeOfStorage);

        req.getRequestDispatcher("/views/about.jsp").forward(req, resp);
    }
}
