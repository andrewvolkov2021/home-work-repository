package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    private final IProductService productService;

    public ProductRestController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "name", required = false) String name){

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products = productService.getAll(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        try {
            Product product = productService.get(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        try {
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update,
                                           @RequestBody Product product) {

        try {
            productService.update(product, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            productService.delete(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
