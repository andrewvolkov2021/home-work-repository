package by.it_academy.jd2.my_application.servicies.api;

import by.it_academy.jd2.my_application.models.Product;

import java.util.List;

public interface IProductService {
    /**
    Создание нового продукта
     @param product - продукт для создания
     */
    void createProduct(Product product);

    /**
     * Возвращает список всех имеющихся продуктов
     * @return список продуктов
     */
    List<Product> getAllProducts();

    /**
     * Получение карточки продукта
     * @param id - ID продукта
     * @return - объект продукта с заданным ID.
     * Если пролукт с заданным ID не найден, метод возвращает null
     */
    Product getProduct(long id);

    /**
     * Обнавляет продукт с заданным ID,
     * в соответствии с переданным продуктом
     * @param product - продукт, в соответствии с которым нужно обновить данные
     * @param id - ID продукта, которого нужно обновить
     */
     void updateProduct(Product product, long id);

    /**
     * Удаляет продукт с заданным ID
     * @param id - ID продукта, котороый необходимо удалить
     */
    void deleteProduct(long id);
}
