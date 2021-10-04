package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.SearchEmployee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.file_storage.readers.DBEmployeeReader;

import java.util.List;

public class SelectEmployeeServiceOLD {
    private static SelectEmployeeServiceOLD instance = new SelectEmployeeServiceOLD();

    private SelectEmployeeServiceOLD(){
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

    public List<Employee> getFullList(SearchEmployee searchEmployee){
        return DBEmployeeReader.getInstance().getFullSortedListOfEmployees(searchEmployee);
    }

    public List<Employee> getSortedList(long page, long limit, List<Employee> fullList){
        long offset = (page - 1) * limit;
        return DBEmployeeReader.getInstance().getSortedListOfEmployee(offset, limit, fullList);
    }

    public long getCountOfSortedPages(long limit, List<Employee> employees){
        long countOfRecords = employees.size();
        long countOfPages = countOfRecords / limit;
        if (countOfRecords % limit != 0){
            countOfPages++;
        }
        return countOfPages;
    }

    public static SelectEmployeeServiceOLD getInstance(){
        return instance;
    }
}
