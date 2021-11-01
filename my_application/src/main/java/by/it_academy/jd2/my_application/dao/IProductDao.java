package by.it_academy.jd2.my_application.dao;

import by.it_academy.jd2.my_application.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDao extends JpaRepository<Product, Long> {
}
