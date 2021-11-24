package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.JournalByDateDto;
import by.it_academy.jd2.my_application.dto.JournalDto;
import by.it_academy.jd2.my_application.models.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalService {

    Page<Journal> getAllByProfileId(Pageable pageable, Long profileId);

    JournalByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end, Long id);

    List<Journal> findAllByProfile(Long id);

    Journal save(JournalDto journalDto, Long idProfile);

    Page<Journal> getAll (Pageable pageable);

    Journal get(Long id);

    void update(JournalDto journalDto, Long id, LocalDateTime dtUpdate, Long idProfile);

    void delete (Long id, LocalDateTime dtUpdate);
}


