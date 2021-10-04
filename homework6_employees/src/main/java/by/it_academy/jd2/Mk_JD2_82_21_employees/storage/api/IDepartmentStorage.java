package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;

import java.util.List;
import java.util.Map;

public interface IDepartmentStorage {
    Map<Long, Department> getMapOfDepartments();

    Department getDepartment(long id);

    List<Department> getListOfDepartments();

     void autoAddingDepartments(List<Department> listOfDepartments);

    List<Long> getListOfDepartmentId();

    void autoAddingParentalDepartment(Long[] array, Integer[] arrayOfParentalDepartment);
    }
