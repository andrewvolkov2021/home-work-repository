package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.fillers.DBEmployeeFillerManually;

public class AddEmployeeService {
    private static AddEmployeeService instance = new AddEmployeeService();

    private AddEmployeeService(){
    }

    public long addNewEmployee(String name, double salary){
        return DBEmployeeFillerManually.getInstance().addEmployee(name, salary);
    }

    public static AddEmployeeService getInstance(){
        return instance;
    }
}
