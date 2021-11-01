package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.IProductDao;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.servicies.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductDao productDao;

    @Autowired
    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public void updateProduct(Product product, long id) {
        Product updatedProduct = getProduct(id);
        updatedProduct.setName(product.getName());
        updatedProduct.setBrand(product.getBrand());
        updatedProduct.setCalories(product.getCalories());
        updatedProduct.setProteins(product.getProteins());
        updatedProduct.setFats(product.getFats());
        updatedProduct.setMeasure(product.getMeasure());
        productDao.save(updatedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productDao.deleteById(id);
    }
}
