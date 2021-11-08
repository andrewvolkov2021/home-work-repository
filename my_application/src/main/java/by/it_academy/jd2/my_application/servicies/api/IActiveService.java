package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Active;

import java.time.LocalDateTime;
import java.util.List;

public interface IActiveService {
    /**
     * Возвращает список активностей пользователя
     * @param page - номер запрашиваемой станицы
     * @param size - количество активностей пользователя на странице
     * @param begin - начало периода времени, с которого нужно выдавать активности пользователя
     * @param end - конец периода времени, до которого нужно выдавать активности пользователя
     * @return - список активностей пользователя
     */
    List<Active> getListOfActives(long page, long size, LocalDateTime begin, LocalDateTime end);

    /**
     * Создание записи новой активности пользователя
     * @param active  - активность пользователя для сознания
     */
    void createActive(Active active);

    /**
     * Поучение карточки активности пользователя
     * @param id - ID активности пользователя
     * @return - активность пользователя с заданным ID
     */
    Active getActive(long id);

    /**
     * Обновляет запись активности пользователя с заданным ID,
     * в соответствии с переданной активностью
     * @param active - активность пользователя, в соответствии с которой нужно обновить данные
     * @param id - ID активности, которую необходимо обновить
     * @param dt_update - время последнего обновления
     */
    void updateActive(Active active, long id, LocalDateTime dt_update);

    /**
     * Удаляет запись активности пользователя с заданным ID
     * @param id - ID активности, которую необходимо удалить
     * @param dt_update - время последнего обновления
     */
    void deleteActive(long id, LocalDateTime dt_update);
}
