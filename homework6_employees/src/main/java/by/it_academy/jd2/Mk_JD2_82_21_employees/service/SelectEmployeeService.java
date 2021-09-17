package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBEmployeeReader;

import java.util.List;
import java.util.Map;

public class SelectEmployeeService {
    private static SelectEmployeeService instance = new SelectEmployeeService();

    private SelectEmployeeService(){
    }

    public List<Employee> getSelectListOfEmployee(long limit, long page){
        Map<Long, Department> mapOfDepartments = DepartmentService.getInstance().getMapOfDepartment();
        Map<Long, Position> mapOfPositions = PositionService.getInstance().getMapOfPositions();

        long offset = (page - 1) * limit;
        return DBEmployeeReader.getInstance().getSelectListOfEmployee(limit, offset, mapOfDepartments, mapOfPositions);
    }

    public long getCountOfPages(long limit){
        long countOfRecords = DBEmployeeReader.getInstance().getCountOfRecords();
        long countOfPages = countOfRecords / limit;
        if (countOfRecords % limit != 0){
            countOfPages++;
        }
        return countOfPages;
    }

    public long[] getArrayOfPages(long page, long maxPage){
        long[] pages = new long[9];
        pages[4] = page;
        for (int i = 1, a = 1; i <= 4 && a <= 4; i++){
            if ((page - i) > 0) {
                long min = page - i;
                pages[(4 - i)] = min;
                a++;
            }
            if ((page + i) <= maxPage){
                long max = page + i;
                pages[(4 + i)] = max;
                a++;
            }
        }
        return pages;
    }

    public Employee getEmployee(long id){
        Map<Long, Department> mapOfDepartments = DepartmentService.getInstance().getMapOfDepartment();
        Map<Long, Position> mapOfPositions = PositionService.getInstance().getMapOfPositions();
        return DBEmployeeReader.getInstance().getEmployee(id, mapOfDepartments, mapOfPositions);
    }

    public static SelectEmployeeService getInstance(){
        return instance;
    }
}
