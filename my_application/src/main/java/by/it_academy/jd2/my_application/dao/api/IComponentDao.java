package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComponentDao extends JpaRepository<Component, Long> {
}
