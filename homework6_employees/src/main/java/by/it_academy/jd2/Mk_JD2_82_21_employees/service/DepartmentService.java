package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBDepartmentReader;

import java.util.List;
import java.util.Map;

public class DepartmentService {
    private static DepartmentService instance = new DepartmentService();

    private DepartmentService(){
    }

    public List<Department> getListOfDepartments(){
        return DBDepartmentReader.getInstance().getListOfDepartments();
    }

    public Map<Long, Department> getMapOfDepartment(){
        return DBDepartmentReader.getInstance().getMapOfDepartments();
    }

    public Department getDepartment(long id){
        return DBDepartmentReader.getInstance().getDepartment(id);
    }

    public static DepartmentService getInstance(){
        return instance;
    }

}
