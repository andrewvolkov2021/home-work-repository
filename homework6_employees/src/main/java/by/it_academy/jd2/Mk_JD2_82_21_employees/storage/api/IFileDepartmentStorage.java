package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api;

import java.util.List;

public interface IFileDepartmentStorage {
    List<String> getListOfDepartmentsNames();

    List<Integer> getListOfParentalDepartment();
}
