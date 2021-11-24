package by.it_academy.jd2.my_application.services.calculations;

import by.it_academy.jd2.my_application.services.calculations.api.ITimeService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

@Service
public class TimeService implements ITimeService {

    @Override
    public LocalDateTime getDate(long day) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(day), ZoneId.systemDefault());
    }

    @Override
    public LocalDateTime getEndOfDate(LocalDateTime date) {
        return date.with(ChronoField.NANO_OF_DAY, LocalTime.MAX.toNanoOfDay());
    }

    @Override
    public LocalDateTime detStartOfDate(LocalDateTime endOfDate) {
        return endOfDate.minusDays(1L);
    }
}
