package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IProductDao;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IEntityService<Product> {

    private final IProductDao productDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createEntity(Product product) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        product.setCreationDate(creationDate);
        product.setUpdateDate(creationDate);
        productDao.save(product);
    }

    @Override
    public List<Product> getAllEntities() {
        return productDao.findAll();
    }

    @Override
    public Product getEntity(long id) throws IllegalArgumentException  {
        return productDao.findById(id).orElseThrow();
    }

    @Override
    public void updateEntity(Product product, long id) throws IllegalArgumentException {
        Product updatedProduct = getEntity(id);
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

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException {
        productDao.deleteById(id);
    }
}
