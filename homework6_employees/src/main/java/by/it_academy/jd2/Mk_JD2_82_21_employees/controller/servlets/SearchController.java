package by.it_academy.jd2.Mk_JD2_82_21_employees.controller.servlets;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.EmployeeSearchFilter;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPaginationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sorting")
public class SearchController {

    private static final long COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE = 20;

    private final IPaginationService paginationService;
    private final IEmployeeService employeeService;

    public SearchController(IPaginationService paginationService, IEmployeeService employeeService) {
        this.paginationService = paginationService;
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getSortedEmployeeList(Model model, @RequestParam(value = "searchName",
            required = false) String searchName, @RequestParam(value = "minSearchSalary",
            required = false) String minSearchSalary, @RequestParam(value = "maxSearchSalary",
            required = false) String maxSearchSalary, @RequestParam(value = "page",
            required = false) String numberPage) {

        String page;
        if (numberPage == null) {
            page = "1";
        } else {
            page = numberPage;
        }
        String size = Long.toString(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE);

        EmployeeSearchFilter filter = new EmployeeSearchFilter(page, size, searchName, minSearchSalary, maxSearchSalary);
        model.addAttribute("searchEmployee", filter);

        List<Employee> sortedList = employeeService.getSortedList(filter);
        model.addAttribute("listOfEmployees", sortedList);

        List<Employee> fullListWithoutPages = employeeService.getFullSortedList(filter);
        long countOfRecords = fullListWithoutPages.size();

        long startPosition = paginationService
                .getStartPosition(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, Long.parseLong(page));
        model.addAttribute("startPosition", startPosition);

        long countOfPages = paginationService
                .getCountOfPages(COUNT_OF_EMPLOYEES_ON_PAGE_PARAM_MANE, countOfRecords);
        model.addAttribute("countOfPages", countOfPages);

        long[] pages = paginationService.getArrayOfPages(Long.parseLong(page), countOfPages);
        model.addAttribute("pages", pages);
        return "sortedEmployeePage";
    }
}