package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Weighting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IWeighingDao extends JpaRepository<Weighting, Long> {
    Page<Weighting> findAllByCreationDateBetweenAndProfileId(LocalDateTime start, LocalDateTime end,
                                                             Long id, Pageable pageable);
}
