package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IDepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getListOfDepartment(Model model){
            List<Department> listOfDepartments = departmentService.getListOfDepartments();
            model.addAttribute("listOfDepartments", listOfDepartments);
            return "listOfDepartments";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getDepartmentCard(Model model, @PathVariable("id") Long id){
            Department department = departmentService.getDepartment(id);
            model.addAttribute("department", department);
            return "departmentCard";
    }
}
