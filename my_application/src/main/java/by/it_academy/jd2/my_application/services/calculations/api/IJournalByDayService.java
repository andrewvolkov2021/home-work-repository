package by.it_academy.jd2.my_application.services.calculations.api;

import by.it_academy.jd2.my_application.dto.JournalByDateDto;
import by.it_academy.jd2.my_application.models.Journal;

import java.util.List;

public interface IJournalByDayService {

    JournalByDateDto getJournalByDay(List<Journal> journals);
}
