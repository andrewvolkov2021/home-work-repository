package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.AutoFiller;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IAutoFillerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper mapper = new ObjectMapper();

    public AutoFillRestController(IAutoFillerService autoFillerService) {
        this.autoFillerService = autoFillerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void fillDateBase(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        AutoFiller autoFiller = mapper.readValue(req.getInputStream(), AutoFiller.class);
        autoFillerService.fillDateBase(autoFiller);
        resp.sendRedirect("start");
    }
}
