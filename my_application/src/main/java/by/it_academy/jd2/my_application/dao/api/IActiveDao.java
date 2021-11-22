package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Active;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IActiveDao extends JpaRepository<Active, Long> {
    Page<Active> findAllByCreationDateBetweenAndProfileId(LocalDateTime start, LocalDateTime end,
                                                          Long id, Pageable pageable);
}
