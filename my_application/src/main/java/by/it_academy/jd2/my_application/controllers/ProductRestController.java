package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.ProductDto;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProductService;
import by.it_academy.jd2.my_application.utils.TimeConversion;
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
    private final TimeConversion timeConversion;

    public ProductRestController(IProductService productService, TimeConversion timeConversion){
        this.productService = productService;
        this.timeConversion = timeConversion;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "name", required = false) String name){

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products;
        if (name != null) {
            products = productService.getAll(name, pageable);
        } else {
            products = productService.getAll(pageable);
        }
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
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        try {
            productService.save(productDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") Long dtUpdate,
                                           @RequestBody ProductDto productDto) {

        try {
            LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
            productService.update(productDto, id, dtUpdateTime);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") Long dtUpdate) {
        try {
            LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
            productService.delete(id, dtUpdateTime);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
