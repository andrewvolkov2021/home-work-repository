package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets.oldServlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IAutoFillerService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AutoFillServlet", urlPatterns = "/autoFillOLD")
public class AutoFillServlet extends HttpServlet {

    private static final String EMPLOYEE_COUNT_PARAM_MANE = "count";

    private final IAutoFillerService autoFillerService;

    public AutoFillServlet() {
        this.autoFillerService = ApplicationContextUtil.getContext().getBean(IAutoFillerService.class);
    }

   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int count = Integer.parseInt(req.getParameter(EMPLOYEE_COUNT_PARAM_MANE));
        autoFillerService.fillDateBase(count);

        req.getRequestDispatcher("/views/start.jsp").forward(req, resp);
    }
    */
}

