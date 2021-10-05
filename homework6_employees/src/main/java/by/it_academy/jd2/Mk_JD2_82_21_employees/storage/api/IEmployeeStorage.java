package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.EmployeeSearchFilter;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;

import java.util.List;
import java.util.Map;

public interface IEmployeeStorage {

    long addEmployee(Employee employee);

    Employee getEmployee(long id, Map<Long, Department> mapOfDepartment, Map<Long, Position> mapOfPosition);

    List<Employee> getListOfEmployees(long limit, long offset, Map<Long, Department> mapOfDepartments,
                                     Map<Long, Position> mapOfPositions);

    long getCountOfRecords();

    void autoAddingOfEmployees(List<Employee> listOfEmployee);

    List<Employee> getSortedList(EmployeeSearchFilter filter);

    List<Employee> getFullSortedList(EmployeeSearchFilter filter);
}
