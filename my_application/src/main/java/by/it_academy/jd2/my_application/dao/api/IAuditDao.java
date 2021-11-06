package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuditDao extends JpaRepository<Audit, Long> {
}
