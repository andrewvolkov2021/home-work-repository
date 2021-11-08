package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.servicies.api.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    private final IProductService productService;

    public ProductRestController(IProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        try {
            productService.createProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update,
                                           @RequestBody Product product) {
        try {
            productService.updateProduct(product, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            productService.deleteProduct(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("name") String name){
        List<Product> products = productService.getListOfProducts(page, size, name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
