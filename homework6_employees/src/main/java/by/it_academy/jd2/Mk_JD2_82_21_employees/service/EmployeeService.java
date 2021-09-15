package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBEmployeeReader;

import java.util.List;
import java.util.Map;

public class EmployeeService {
    private static EmployeeService instance = new EmployeeService();

    private EmployeeService(){
    }

    public List<Employee> getListOfEmployee(){
        Map<Long, Department> mapOfDepartments = DepartmentService.getInstance().getMapOfDepartment();
        Map<Long, Position> mapOfPositions = PositionService.getInstance().getMapOfPositions();
        return DBEmployeeReader.getInstance().getListOfEmployee(mapOfDepartments, mapOfPositions);
    }

    public Employee getEmployee(long id){
        Map<Long, Department> mapOfDepartments = DepartmentService.getInstance().getMapOfDepartment();
        Map<Long, Position> mapOfPositions = PositionService.getInstance().getMapOfPositions();
        return DBEmployeeReader.getInstance().getEmployee(id, mapOfDepartments, mapOfPositions);
    }



    public static EmployeeService getInstance(){
        return instance;
    }
}
