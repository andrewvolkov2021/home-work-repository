package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishDao extends JpaRepository<Dish, Long> {
    Page<Dish> findRecipeByNameContains (String name, Pageable pageable);
}
