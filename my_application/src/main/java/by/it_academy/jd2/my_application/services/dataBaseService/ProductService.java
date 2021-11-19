package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IProductDao;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class ProductService implements IProductService {

    private final IProductDao productDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void save(Product product){
        LocalDateTime creationDate = LocalDateTime.now();
        product.setCreationDate(creationDate);
        product.setUpdateDate(creationDate);
        productDao.save(product);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Product get(Long id) throws IllegalArgumentException {
        return productDao.findById(id).orElseThrow();
    }

    @Override
    public void update(Product product, Long id, LocalDateTime dt_update) throws OptimisticLockException {

        Product updatedProduct = get(id);
        if (dt_update != updatedProduct.getUpdateDate()) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемый продукт был изменен");
        } else {
            updatedProduct.setName(product.getName());
            updatedProduct.setBrand(product.getBrand());
            updatedProduct.setCalories(product.getCalories());
            updatedProduct.setProteins(product.getProteins());
            updatedProduct.setFats(product.getFats());
            updatedProduct.setCarbohydrates(product.getCarbohydrates());
            updatedProduct.setMeasure(product.getMeasure());
            updatedProduct.setCreator(product.getCreator());
            updatedProduct.setCreationDate(product.getUpdateDate());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedProduct.setUpdateDate(updateDate);

            productDao.save(updatedProduct);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dt_update) throws OptimisticLockException {
        Product deletedProduct = get(id);
        if (dt_update != deletedProduct.getUpdateDate()) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как удаляемый " +
                    "продукт был изменен");
        } else {
            productDao.deleteById(id);
        }
    }
}
