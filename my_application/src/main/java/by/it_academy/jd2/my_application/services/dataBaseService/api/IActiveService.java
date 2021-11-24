package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.dto.ActiveDto;
import by.it_academy.jd2.my_application.models.Active;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IActiveService{

    Active save(ActiveDto activeDto);

    ActiveByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end,
                                                      Long id, Pageable pageable);

    Active get(Long id);

    void update(ActiveDto activeDto, Long id, LocalDateTime dtUpdate);

    void delete (Long id, LocalDateTime dtUpdate);
}
