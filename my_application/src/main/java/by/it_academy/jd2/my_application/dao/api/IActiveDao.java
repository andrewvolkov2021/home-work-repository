package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Active;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActiveDao extends JpaRepository<Active, Long> {
}
