package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.models.Active;

import java.time.LocalDateTime;

public interface IActiveService{

    void save(Active active);

    ActiveByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end, Long id);

    Active get(Long id);

    void update(Active active, Long id, LocalDateTime dt_update);

    void delete (Long id, LocalDateTime dt_update);
}
