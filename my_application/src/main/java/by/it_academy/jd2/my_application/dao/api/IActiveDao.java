package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IActiveDao extends JpaRepository<Active, Long> {
    List<Active> findAllByCreationDateBetweenAndProfileId(LocalDateTime start, LocalDateTime end, Long id);
}
