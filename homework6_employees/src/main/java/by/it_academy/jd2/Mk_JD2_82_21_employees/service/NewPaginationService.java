package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPaginationService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQLStorage.EmployeeStorage;

public class NewPaginationService implements IPaginationService {

    private static NewPaginationService instance = new NewPaginationService();

    private NewPaginationService() {
    }

    @Override
    public long getCountOfPages(long count) {
        long countOfRecords = EmployeeStorage.getInstance().getCountOfRecords();
        long countOfPages = countOfRecords / count;
        if (countOfRecords % count != 0){
            countOfPages++;
        }
        return countOfPages;
    }

    @Override
    public long[] getArrayOfPages(long page, long countOfPages) {
        long[] pages = new long[9];
        pages[4] = page;
        for (int i = 1, a = 1; i <= 4 && a <= 4; i++){
            if ((page - i) > 0) {
                long min = page - i;
                pages[(4 - i)] = min;
                a++;
            }
            if ((page + i) <= countOfPages){
                long max = page + i;
                pages[(4 + i)] = max;
                a++;
            }
        }
        return pages;
    }

    public static NewPaginationService getInstance(){
        return instance;
    }
}
