package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.EmployeeSearchFilter;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPositionService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IEmployeeStorage;

import java.util.List;
import java.util.Map;

public class NewEmployeeService implements IEmployeeService {

    private final IEmployeeStorage employeeStorage;
    private final IDepartmentService departmentService;
    private final IPositionService positionService;

    public NewEmployeeService(IEmployeeStorage employeeStorage, IDepartmentService departmentService,
                              IPositionService positionService){
        this.employeeStorage = employeeStorage;
        this.departmentService = departmentService;
        this.positionService = positionService;
    }
    
    @Override
    public long addEmployee(Employee employee) {
        return employeeStorage.addEmployee(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        Map<Long, Department> mapOfDepartments =departmentService.getMapOfDepartments();
        Map<Long, Position> mapOfPositions = positionService.getMapOfPositions();
        return employeeStorage.getEmployee(id, mapOfDepartments, mapOfPositions);
    }

    @Override
    public List<Employee> getListOfEmployees(long limit, long page) {
        Map<Long, Department> mapOfDepartments = departmentService.getMapOfDepartments();
        Map<Long, Position> mapOfPositions = positionService.getMapOfPositions();

        long offset = (page - 1) * limit;
        return employeeStorage.getListOfEmployees(limit, offset, mapOfDepartments, mapOfPositions);
    }

    @Override
    public void autoAddingOfEmployees(List<Employee> listOfEmployee) {
        employeeStorage.autoAddingOfEmployees(listOfEmployee);
    }

    @Override
    public List<Employee> getSortedList(EmployeeSearchFilter filter) {
        return employeeStorage.getSortedList(filter);
    }

    @Override
    public List<Employee> getFullSortedList(EmployeeSearchFilter filter) {
        return employeeStorage.getFullSortedList(filter);
    }

    @Override
    public long getCountOfRecords() {
        return employeeStorage.getCountOfRecords();
    }
}
