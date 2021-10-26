package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.AutoFiller;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IAutoFillerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/autoFiller")
public class AutoFillRestController {

    private final IAutoFillerService autoFillerService;

    public AutoFillRestController(IAutoFillerService autoFillerService) {
        this.autoFillerService = autoFillerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void fillDateBase(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        AutoFiller autoFiller = (AutoFiller) req.getAttribute("autoFiller");
        autoFillerService.fillDateBase(autoFiller);
        resp.sendRedirect("start");
    }
}
