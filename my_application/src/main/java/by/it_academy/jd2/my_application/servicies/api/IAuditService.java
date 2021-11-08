package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Audit;

import java.time.LocalDateTime;
import java.util.List;

public interface IAuditService {
    /**
     * Возвращает список изменений состояний системы
     * @param page - номер запрашиваемой станицы
     * @param size - количество взешиваний на странице
     * @param begin - начало периода времени, с которого нужно выдавать изменения состояния системы
     * @param end - конец периода времени, до которого нужно выдавать изменения состояния системы
     * @return - список изменений состояний системы
     */
    List<Audit> getListOfAudits(long page, long size, LocalDateTime begin, LocalDateTime end);

    /**
     * Создание описания нового аудита
     * @param audit - аудит для сознания
     */
    void createAudit(Audit audit);

    /**
     * Поучение карточки изменения
     * @param id - ID изменения
     * @return - изменение с заданным ID
     */
    Audit getAudit(long id);

    /**
     * Удаляет описание изменеия с заданным ID
     * @param id - ID описания изменения, которое необходимо удалить
     * @param dt_update - дата последнего обновления
     */
    void deleteAudit(long id, LocalDateTime dt_update);
}
