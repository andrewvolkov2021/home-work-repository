package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Journal;

import java.time.LocalDateTime;
import java.util.List;

public interface IJournalService {
    /**
     * Возвращает список приемов пищи
     * @param page - номер запрашиваемой станицы
     * @param size - количество взешиваний на странице
     * @return - список приемов пищи
     */
    List<Journal> getListOfJournals(long page, long size);

    /**
     * Возвращает список приемов пищи за день
     * @param day - день, за который нужно выдать приемы пищи
     * @return - список приемов пищи за день
     */
    List<Journal> getListOfJournalsFPerDay(LocalDateTime day);

    /**
     * Создание нового приема пищи
     * @param journal - прием пищи для сознания
     */
    void createJournal(Journal journal);

    /**
     * Поучение карточки приема пищи
     * @param id - ID приема пищи
     * @return - прием пищи с заданным ID
     */
    Journal getJournal(long id);

    /**
     * Обновляет прием пищи с заданным ID,
     * в соответствии с переданными данными о приеме пищп
     * @param journal - прием пищи, в соответствии с которым нужно обновить данные
     * @param id - ID приема пищи, который необходимо обновить
     * @param dt_update - время последнего обновления
     */
    void updateJournal(Journal journal, long id, LocalDateTime dt_update);

    /**
     * Удаляет прием пищи с заданным ID
     * @param id - ID приема пищи, который необходимо удалить
     * @param dt_update - дата последнего обновления
     */
    void deleteJournal(long id, LocalDateTime dt_update);
}
