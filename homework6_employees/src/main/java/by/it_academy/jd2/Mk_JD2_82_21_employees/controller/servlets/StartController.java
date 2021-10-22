package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StartController{

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
    return "start";
    }
}
