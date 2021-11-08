package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Weighting;

import java.time.LocalDateTime;
import java.util.List;

public interface IWeightingService {
    /**
     * Возвращает список взвешиваний
     * @param page - номер запрашиваемой станицы
     * @param size - количество взешиваний на странице
     * @param begin - начало периода времени, с которого нужно выдавать взвешивания
     * @param end - конец периода времени, до которого нужно выдавать взвешивания
     * @return - список взвешиваний
     */
    List<Weighting> getListOfWeightings(long page, long size, LocalDateTime begin, LocalDateTime end);

    /**
     * Создание нового взвешивания
     * @param weighting - взвешивание для сознания
     */
    void createWeighting(Weighting weighting);

    /**
     * Поучение карточки взвешивания
     * @param id - ID взвешивания
     * @return - взвешивание с заданным ID
     */
    Weighting getWeighting(long id);

    /**
     * Обновляет взвешивание с заданным ID,
     * в соответствии с переданным взвешиванием
     * @param weighting - взвешивание, в соответствии с которым нужно обновить данные
     * @param id - ID взвешивания, которое необходимо обновить
     */
    void updateWeighting(Weighting weighting, long id, LocalDateTime dt_update);

    /**
     * Удаляет взвешивание с заданным ID
     * @param id - ID взвешивания, которое необходимо удалить
     * @param dt_update - дата последнего обновления
     */
    void deleteWeighting(long id, LocalDateTime dt_update);
}
