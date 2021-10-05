package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage.DepartmentStorageHibernate;

import java.util.List;
import java.util.Map;

public class NewDepartmentService implements IDepartmentService {

    private static final NewDepartmentService instance = new NewDepartmentService();

    private NewDepartmentService() {
    }

    @Override
    public Map<Long, Department> getMapOfDepartments() {
        return DepartmentStorageHibernate.getInstance().getMapOfDepartments();
    }

    @Override
    public List<Department> getListOfDepartments() {
        return DepartmentStorageHibernate.getInstance().getListOfDepartments();
    }

    @Override
    public Department getDepartment(long id) {
        return DepartmentStorageHibernate.getInstance().getDepartment(id);
    }

    @Override
    public void autoAddingDepartment(List<Department> listOfDepartment) {
        DepartmentStorageHibernate.getInstance().autoAddingDepartments(listOfDepartment);
    }

    @Override
    public List<Long> getListOfDepartmentId() {
        return DepartmentStorageHibernate.getInstance().getListOfDepartmentId();
    }

    @Override
    public void autoAddingParentalDepartment(Long[] array, Integer[] arrayOfParentalDepartment) {
        DepartmentStorageHibernate.getInstance().autoAddingParentalDepartment(array, arrayOfParentalDepartment);
    }

    public static NewDepartmentService getInstance(){
        return instance;
    }
}
