package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductService {
    /**
     * Возвращает список продуктов
     * @param page - номер запрашиваемой станицы
     * @param size - количество взешиваний на странице
     * @param name - название продукта
     * @return - список продуктов
     */
    List<Product> getListOfProducts(long page, long size, String name);

    /**
     * Создание нового продукта
     * @param product - продукт для сознания
     */
    void createProduct(Product product);

    /**
     * Поучение карточки продукта
     * @param id - ID продукта
     * @return - продукт с заданным ID
     */
    Product getProduct(long id);

    /**
     * Обновляет продукт с заданным ID,
     * в соответствии с переданным продуктом
     * @param product - продукт, в соответствии с которым нужно обновить данные
     * @param id - ID продукта, который необходимо обновить
     * @param dt_update - время последнего обновления
     */
    void updateProduct(Product product, long id, LocalDateTime dt_update);

    /**
     * Удаляет продукт с заданным ID
     * @param id - ID продукта, который необходимо удалить
     * @param dt_update - дата последнего обновления
     */
    void deleteProduct(long id, LocalDateTime dt_update);
}
