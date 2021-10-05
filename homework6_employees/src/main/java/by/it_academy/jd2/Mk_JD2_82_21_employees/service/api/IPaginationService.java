package by.it_academy.jd2.Mk_JD2_82_21_employees.service.api;

public interface IPaginationService {
    long getCountOfPages(long count, long countOfRecords);

    long[] getArrayOfPages(long page, long countOfPages);

    long getStartPosition(long size, long page);
 }
