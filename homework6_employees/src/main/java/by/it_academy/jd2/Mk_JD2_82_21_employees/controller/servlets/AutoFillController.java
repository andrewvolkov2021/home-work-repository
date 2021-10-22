package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IAutoFillerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/autoFill")
public class AutoFillController{

    private final IAutoFillerService autoFillerService;

    public AutoFillController(IAutoFillerService autoFillerService) {
        this.autoFillerService = autoFillerService;
    }

    @RequestMapping(value = "/{count}", method = RequestMethod.GET)
    public String post(Model model, @PathVariable("count") Integer count){
        autoFillerService.fillDateBase(count);
        return "start";
    }
}
