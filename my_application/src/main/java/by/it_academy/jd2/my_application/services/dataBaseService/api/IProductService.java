package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.ProductDto;
import by.it_academy.jd2.my_application.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IProductService {

    void save(ProductDto productDto);

    Page<Product> getAll(Pageable pageable);

    Page<Product> getAll(String name, Pageable pageable);

    Product get(Long id);

    void update(ProductDto productDto, Long id, LocalDateTime dtUpdate);

    void delete (Long id, LocalDateTime dtUpdate);
}
