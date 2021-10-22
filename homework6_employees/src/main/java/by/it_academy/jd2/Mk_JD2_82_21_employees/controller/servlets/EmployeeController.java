package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPaginationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController{

    private static final long COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE = 20;

    private final IEmployeeService employeeService;
    private final IPaginationService paginationService;

    public EmployeeController(IEmployeeService employeeService, IPaginationService paginationService) {
        this.employeeService = employeeService;
        this.paginationService = paginationService;
    }

    //Постраничный вывод списка сотрудников
    @RequestMapping(method = RequestMethod.GET)
    public String getPageWithEmployees(Model model,
                                       @RequestParam(value = "paramEmployee", required = false) String paramEmployee,
                                       @RequestParam(value = "page", required = false) String numberPage){

        if (paramEmployee != null) {

            long page = 0;
            if (numberPage == null) {
                page = 1;
            } else {
                page = Long.parseLong(numberPage);
            }

            long startPosition = paginationService
                    .getStartPosition(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, page);
            model.addAttribute("startPosition", startPosition);

            List<Employee> listOfEmployees = employeeService.getListOfEmployees(
                    COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, page);
            model.addAttribute("listOfEmployees", listOfEmployees);

            long countOFRecords = employeeService.getCountOfRecords();
            long countOfPages = paginationService.getCountOfPages(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE,
                    countOFRecords);
            model.addAttribute("countOfPages", countOfPages);

            long[] pages = paginationService.getArrayOfPages(page, countOfPages);
            model.addAttribute("pages", pages);
            return "employeesPage";

        } else {
            return "start";
        }
    }

    //Получение карточки сотрудника по ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEmployeeCard(Model model, @PathVariable(value = "id") Long id){

        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "newEmployeeCard";
    }

    //Перенаправление на сервлет для получения карточки по ID
    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public String get(Model model, @RequestParam(value = "getId", required = false) String getId){
       return "getCard";
    }
}
