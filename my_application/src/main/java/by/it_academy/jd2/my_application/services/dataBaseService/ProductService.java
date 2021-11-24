package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IProductDao;
import by.it_academy.jd2.my_application.dto.ProductDto;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class ProductService implements IProductService {

    private final IProductDao productDao;
    private final UserHolder userHolder;

    public ProductService(IProductDao productDao, UserHolder userHolder) {
        this.productDao = productDao;
        this.userHolder = userHolder;
    }

    @Override
    public Product save(ProductDto productDto){
        Product product = new Product();
        product.setCreator(userHolder.getUser());
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCalories(productDto.getCalories());
        product.setProteins(productDto.getProteins());
        product.setCarbohydrates(productDto.getCarbohydrates());
        product.setFats(productDto.getFats());
        product.setMeasure(productDto.getMeasure());

        LocalDateTime creationDate = LocalDateTime.now().withNano(0);
        product.setCreationDate(creationDate);
        product.setUpdateDate(creationDate);
        productDao.save(product);
        return product;
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Page<Product> getAll(String name, Pageable pageable) {
        return productDao.findProductsByNameContains(name, pageable);
    }

    @Override
    public Product get(Long id) throws IllegalArgumentException {
        return productDao.findById(id).orElseThrow();
    }

    @Override
    public void update(ProductDto productDto, Long id, LocalDateTime dtUpdate) throws OptimisticLockException {

        Product updatedProduct = get(id);
        if (!updatedProduct.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемый продукт был изменен");
        } else {
            updatedProduct.setName(productDto.getName());
            updatedProduct.setBrand(productDto.getBrand());
            updatedProduct.setCalories(productDto.getCalories());
            updatedProduct.setProteins(productDto.getProteins());
            updatedProduct.setFats(productDto.getFats());
            updatedProduct.setCarbohydrates(productDto.getCarbohydrates());
            updatedProduct.setMeasure(productDto.getMeasure());
            updatedProduct.setCreator(userHolder.getUser());

            LocalDateTime updateDate = LocalDateTime.now().withNano(0);
            updatedProduct.setUpdateDate(updateDate);

            productDao.save(updatedProduct);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Product deletedProduct = get(id);
        if (!deletedProduct.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как удаляемый " +
                    "продукт был изменен");
        } else {
            productDao.deleteById(id);
        }
    }
}
