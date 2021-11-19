package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.JournalByDateDto;
import by.it_academy.jd2.my_application.models.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalService extends IService<Journal, Long>{

    Page<Journal> getAllByProfileId(Pageable pageable, long profileId);

    JournalByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end, long id);

    List<Journal> findAllByProfile(long id);
}


