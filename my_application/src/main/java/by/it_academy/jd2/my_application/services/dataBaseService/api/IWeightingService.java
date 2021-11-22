package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.WeightingByDateDto;
import by.it_academy.jd2.my_application.dto.WeightingDto;
import by.it_academy.jd2.my_application.models.Weighting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IWeightingService{
    WeightingByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end,
                                                         Long id, Pageable pageable);

    void save(WeightingDto weightingDto);

    Page<Weighting> getAll(Pageable pageable);

    Weighting get(Long id);

    void update(WeightingDto weightingDto, Long id, LocalDateTime dtUpdate);

    void delete(Long id, LocalDateTime dtUpdate);
}
