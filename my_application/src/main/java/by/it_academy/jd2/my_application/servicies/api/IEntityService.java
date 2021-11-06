package by.it_academy.jd2.my_application.servicies.api;

import java.util.List;

public interface IEntityService<T> {
    /**
    Создание новой сущности
     @param entity - сущность для создания
     */
    void createEntity(T entity);

    /**
     * Возвращает список всех имеющихся сущностей
     * @return список сущностей
     */
    List<T> getAllEntities();

    /**
     * Получение карточки сущности
     * @param id - ID сущности
     * @return - сущность с заданным ID.
     * Если сущность с заданным ID не найден, метод возвращает null
     */
    T getEntity(long id);

    /**
     * Обнавляет сущность с заданным ID,
     * в соответствии с переданной сущностью
     * @param entity - сущность, в соответствии с которой нужно обновить данные
     * @param id - ID сущности, которую нужно обновить
     */
     void updateEntity(T entity, long id);

    /**
     * Удаляет сущность с заданным ID
     * @param id - ID продукта, который необходимо удалить
     */
    void deleteEntity(long id);
}
