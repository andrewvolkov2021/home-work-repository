package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.DishDto;
import by.it_academy.jd2.my_application.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IDishService {

    Dish save(DishDto dishDto);

    Page<Dish> getAll(Pageable pageable);

    Page<Dish> getAll(String name, Pageable pageable);

    Dish get(Long id);

    void update(DishDto dishDto, Long id, LocalDateTime dtUpdate);

    void delete (Long id, LocalDateTime dtUpdate);

}
