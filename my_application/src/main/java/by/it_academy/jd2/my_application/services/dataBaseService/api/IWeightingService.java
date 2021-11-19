package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.models.Weighting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IWeightingService extends IService<Weighting, Long>{
    Page<Weighting> findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end,
                                                      Long id, Pageable pageable);
}
