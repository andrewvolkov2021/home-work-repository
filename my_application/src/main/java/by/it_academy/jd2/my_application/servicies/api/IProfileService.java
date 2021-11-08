package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Profile;

import java.time.LocalDateTime;
import java.util.List;

public interface IProfileService {
    /**
     * Возвращает список профилей пользователей
     * @param page - номер запрашиваемой станицы
     * @param size - количество взешиваний на странице
     * @return - список приофилей пользователей
     */
    List<Profile> getListOfProfiles(long page, long size);

    /**
     * Создание нового профиля пользователя
     * @param profile - профиль для сознания
     */
    void createProfile(Profile profile);

    /**
     * Поучение карточки профиля пользователя
     * @param id - ID профиля пользователя
     * @return - профиль пользователя с заданным ID
     */
    Profile getProfile(long id);

    /**
     * Обновляет профиль пользователя с заданным ID,
     * в соответствии с переданным профилем
     * @param profile - профиль пользователя, в соответствии с которым нужно обновить данные
     * @param id - ID профиля пользователя, который необходимо обновить
     * @param dt_update - время последнего обновления профиля пользователя
     */
    void updateProfile(Profile profile, long id, LocalDateTime dt_update);

    /**
     * Удаляет профиль пользователя с заданным ID
     * @param id - ID профиля пользователя, который необходимо удалить
     * @param dt_update - время последнего обновления
     */
    void deleteProfile(long id, LocalDateTime dt_update);
}
