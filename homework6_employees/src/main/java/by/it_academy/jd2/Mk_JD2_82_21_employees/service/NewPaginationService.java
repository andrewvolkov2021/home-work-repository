package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPaginationService;

public class NewPaginationService implements IPaginationService {

    @Override
    public long getCountOfPages(long count, long countOfRecords) {
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

    @Override
    public long getStartPosition(long size, long page) {
        return (page - 1) * size + 1;
    }
}
