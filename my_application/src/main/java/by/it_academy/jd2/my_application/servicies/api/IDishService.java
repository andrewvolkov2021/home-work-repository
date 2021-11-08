package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface IDishService {
    /**
     * Возвращает список блюд
     * @param page - номер запрашиваемой станицы
     * @param size - количество взешиваний на странице
     * @param name - название блюда
     * @return - список блюд
     */
    List<Dish> getListOfDishes(long page, long size, String name);

    /**
     * Создание нового блюда
     * @param dish - блюдо для сознания
     */
    void createDish(Dish dish);

    /**
     * Поучение карточки блюда
     * @param id - ID блюда
     * @return - блюдо с заданным ID
     */
    Dish getDish(long id);

    /**
     * Обновляет блюдо с заданным ID,
     * в соответствии с переданным блюдом
     * @param dish - блюдо, в соответствии с которым нужно обновить данные
     * @param id - ID блюда, которое необходимо обновить
     * @param dt_update - время последнего обновления
     */
    void updateDish(Dish dish, long id, LocalDateTime dt_update);

    /**
     * Удаляет блюдо с заданным ID
     * @param id - ID блюда, которое необходимо удалить
     * @param dt_update - время последнего обновления
     */
    void deleteDish(long id, LocalDateTime dt_update);
}
