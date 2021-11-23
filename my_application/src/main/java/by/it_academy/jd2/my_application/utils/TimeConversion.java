package by.it_academy.jd2.my_application.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Component
public class TimeConversion {

    public LocalDateTime conversionTime(Long dateUpdate) {
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
        return date;
    }
}

