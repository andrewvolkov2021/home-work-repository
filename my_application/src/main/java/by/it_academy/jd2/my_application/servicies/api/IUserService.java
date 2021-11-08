package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IUserService {
    /**
     * Возвращает список пользователей
     * @param page - номер запрашиваемой станицы
     * @param size - количество взешиваний на странице
     * @param name - имя пользователя
     * @param begin - начало периода времени, с которого нужно выдавать зарегестрированных пользователей
     * @param end - конец периода времени, до которого нужно выдавать зарегестрированных пользователей
     * @return - список пользователей
     */
    List<User> getListOfUsers(long page, long size, String name, LocalDateTime begin, LocalDateTime end);

    /**
     * Создание нового пользователя
     * @param user - пользователь для сознания
     */
    void createUser(User user);

    /**
     * Поучение карточки пользователя
     * @param id - ID пользователя
     * @return - пользователь с заданным ID
     */
    User getUser(long id);

    /**
     * Обновляет пользователя с заданным ID,
     * в соответствии с переданным пользователем
     * @param user - пользователь, в соответствии с которым нужно обновить данные
     * @param id - ID пользователя, которого необходимо обновить
     * @param dt_update - время последнего обновления
     */
    void updateUser(User user, long id, LocalDateTime dt_update);

    /**
     * Удаляет пользователя с заданным ID
     * @param id - ID пользователя, которого необходимо удалить
     * @param dt_update - время последнего обновления
     */
    void deleteUser(long id, LocalDateTime dt_update);
}
