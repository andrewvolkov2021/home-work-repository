package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.fillers.DBDepartmentFiller;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.fillers.DBEmployeeFiller;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.fillers.DBPositionFiller;

import java.util.List;

public class DBFillerService {
    private static DBFillerService instance = new DBFillerService();

    private DBFillerService(){
    }

    public void fillDateBase(int countEmployee){
        DBPositionFiller.getInstance().autoAddingPositions();
        Long[] arrayOfPositionId = DBPositionFiller.getInstance().getArrayOfPositionId();

        DBDepartmentFiller.getInstance().autoAddingDepartment();
        Long[] arrayOfDepartmentId = DBDepartmentFiller.getInstance().getArrayOfDepartmentId();

        DBEmployeeFiller.getInstance().autoAddingOfEmployees(countEmployee,arrayOfDepartmentId, arrayOfPositionId);
    }

    public static DBFillerService getInstance(){
        return instance;
    }
}