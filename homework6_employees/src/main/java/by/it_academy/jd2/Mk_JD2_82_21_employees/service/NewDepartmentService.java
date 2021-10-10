package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IDepartmentStorage;

import java.util.List;
import java.util.Map;

public class NewDepartmentService implements IDepartmentService {

    private final IDepartmentStorage departmentStorage;

    public NewDepartmentService(IDepartmentStorage departmentStorage){
        this.departmentStorage = departmentStorage;
    }

    @Override
    public Map<Long, Department> getMapOfDepartments() {
        return departmentStorage.getMapOfDepartments();
    }

    @Override
    public List<Department> getListOfDepartments() {
        return departmentStorage.getListOfDepartments();
    }

    @Override
    public Department getDepartment(long id) {
        return departmentStorage.getDepartment(id);
    }

    @Override
    public void autoAddingDepartment(List<Department> listOfDepartment) {
        departmentStorage.autoAddingDepartments(listOfDepartment);
    }

    @Override
    public List<Long> getListOfDepartmentId() {
        return departmentStorage.getListOfDepartmentId();
    }

    @Override
    public void autoAddingParentalDepartment(Long[] array, Integer[] arrayOfParentalDepartment) {
        departmentStorage.autoAddingParentalDepartment(array, arrayOfParentalDepartment);
    }
}
