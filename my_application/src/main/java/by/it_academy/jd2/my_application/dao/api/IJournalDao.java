package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJournalDao extends JpaRepository<Journal, Long> {
}
