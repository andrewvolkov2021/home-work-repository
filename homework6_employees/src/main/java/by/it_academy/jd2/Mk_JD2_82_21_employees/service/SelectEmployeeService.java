package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;

import java.util.List;

public class SelectEmployeeService {

    private static final SelectEmployeeService instance = new SelectEmployeeService();

    private SelectEmployeeService(){
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

    public long getCountOfSortedPages(long limit, List<Employee> employees){
        long countOfRecords = employees.size();
        long countOfPages = countOfRecords / limit;
        if (countOfRecords % limit != 0){
            countOfPages++;
        }
        return countOfPages;
    }

    public static SelectEmployeeService getInstance(){
        return instance;
    }
}
