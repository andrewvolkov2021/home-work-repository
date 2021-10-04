package by.it_academy.jd2.Mk_JD2_82_21_employees.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;

import java.util.List;
import java.util.Map;

public interface IDepartmentService {
    Map<Long, Department> getMapOfDepartments();

    List<Department> getListOfDepartments();

    Department getDepartment(long id);

    void autoAddingDepartment(List<Department> listOfDepartment);

    List<Long> getListOfDepartmentId();

    void autoAddingParentalDepartment(Long[] array, Integer[] arrayOfParentalDepartment);
}
