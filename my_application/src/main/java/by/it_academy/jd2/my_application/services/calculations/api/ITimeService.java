package by.it_academy.jd2.my_application.services.calculations.api;

import java.time.LocalDateTime;

public interface ITimeService {
    LocalDateTime getDate(int day);

    LocalDateTime getEndOfDate(LocalDateTime date);

    LocalDateTime detStartOfDate(LocalDateTime endOfDate);
}
