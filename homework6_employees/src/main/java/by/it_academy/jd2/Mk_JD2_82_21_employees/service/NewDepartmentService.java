package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage.DepartmentStorage;

import java.util.List;
import java.util.Map;

public class NewDepartmentService implements IDepartmentService {

    private static final NewDepartmentService instance = new NewDepartmentService();

    private NewDepartmentService() {
    }

    @Override
    public Map<Long, Department> getMapOfDepartments() {
        return DepartmentStorage.getInstance().getMapOfDepartments();
    }

    @Override
    public List<Department> getListOfDepartments() {
        return DepartmentStorage.getInstance().getListOfDepartments();
    }

    @Override
    public Department getDepartment(long id) {
        return DepartmentStorage.getInstance().getDepartment(id);
    }

    @Override
    public void autoAddingDepartment(List<Department> listOfDepartment) {
        DepartmentStorage.getInstance().autoAddingDepartments(listOfDepartment);
    }

    @Override
    public List<Long> getListOfDepartmentId() {
        return DepartmentStorage.getInstance().getListOfDepartmentId();
    }

    @Override
    public void autoAddingParentalDepartment(Long[] array, Integer[] arrayOfParentalDepartment) {
        DepartmentStorage.getInstance().autoAddingParentalDepartment(array, arrayOfParentalDepartment);
    }

    public static NewDepartmentService getInstance(){
        return instance;
    }
}
