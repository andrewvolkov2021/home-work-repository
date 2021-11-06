package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishDao extends JpaRepository<Dish, Long> {
}
