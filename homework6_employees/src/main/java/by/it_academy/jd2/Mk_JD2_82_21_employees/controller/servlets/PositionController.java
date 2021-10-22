package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController{

    private final IPositionService positionService;

    public PositionController(IPositionService positionService){
        this.positionService = positionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getListOfPosition(Model model) {
            List<Position> listOfPositions = positionService.getListOfPositions();
            model.addAttribute("listOfPositions", listOfPositions);
            return "listOfPositions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPositionCard(Model model, @PathVariable("id") Long id) {
        Position position = positionService.getPosition(id);
        model.addAttribute("position", position);
        return "positionCard";
    }
}
