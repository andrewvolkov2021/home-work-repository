package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/employee")
public class EmployeeRestController {

    private final IEmployeeService employeeService;
    private final ObjectMapper mapper = new ObjectMapper();

    public EmployeeRestController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNewEmployee(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        Employee employee = mapper.readValue(req.getInputStream(), Employee.class);
        long id = employeeService.addEmployee(employee);
        model.addAttribute("id", id);
        resp.sendRedirect("newID");
    }
}
