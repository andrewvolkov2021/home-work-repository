package by.it_academy.jd2.Mk_JD2_82_21_employees.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;

import java.util.List;

public interface IEmployeeService {
    long addEmployee(Employee employee);

    Employee getEmployee(long id);

    List<Employee> getListOfEmployees(long limit, long page);

    void autoAddingOfEmployees(List<Employee> listOfEmployee);

}
