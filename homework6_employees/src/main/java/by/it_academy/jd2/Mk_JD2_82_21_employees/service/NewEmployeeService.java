package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.EmployeeSearchFilter;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage.EmployeeStorageHibernate;

import java.util.List;
import java.util.Map;

public class NewEmployeeService implements IEmployeeService {

    private static final NewEmployeeService instance = new NewEmployeeService();

    private NewEmployeeService() {
    }

    @Override
    public long addEmployee(Employee employee) {
        return EmployeeStorageHibernate.getInstance().addEmployee(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        Map<Long, Department> mapOfDepartments = NewDepartmentService.getInstance().getMapOfDepartments();
        Map<Long, Position> mapOfPositions = NewPositionService.getInstance().getMapOfPositions();
        return EmployeeStorageHibernate.getInstance().getEmployee(id, mapOfDepartments, mapOfPositions);
    }

    @Override
    public List<Employee> getListOfEmployees(long limit, long page) {
        Map<Long, Department> mapOfDepartments = NewDepartmentService.getInstance().getMapOfDepartments();
        Map<Long, Position> mapOfPositions = NewPositionService.getInstance().getMapOfPositions();

        long offset = (page - 1) * limit;
        return EmployeeStorageHibernate.getInstance().getListOfEmployees(limit, offset, mapOfDepartments, mapOfPositions);
    }

    @Override
    public void autoAddingOfEmployees(List<Employee> listOfEmployee) {
        EmployeeStorageHibernate.getInstance().autoAddingOfEmployees(listOfEmployee);
    }

    @Override
    public List<Employee> getSortedList(EmployeeSearchFilter filter) {
        return EmployeeStorageHibernate.getInstance().getSortedList(filter);
    }

    @Override
    public List<Employee> getFullSortedList(EmployeeSearchFilter filter) {
        return EmployeeStorageHibernate.getInstance().getFullSortedList(filter);
    }

    @Override
    public long getCountOfRecords() {
        return EmployeeStorageHibernate.getInstance().getCountOfRecords();
    }

    public static NewEmployeeService getInstance(){
        return instance;
    }
}
