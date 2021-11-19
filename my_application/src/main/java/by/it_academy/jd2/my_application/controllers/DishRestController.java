package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IDishService;
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
@RequestMapping("/api/recipe")
public class DishRestController {

    private final IDishService dishService;

    public DishRestController(IDishService dishService){
        this.dishService = dishService;
    }


    @GetMapping
    public ResponseEntity<Page<Dish>> getAllDishes(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                   @RequestParam(value = "name", required = false) String name){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Dish> dishes = dishService.getAll(pageable);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDish(@PathVariable("id") Long id) {
        try {
            Dish dish = dishService.get(id);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createDish(@RequestBody Dish dish){
        try {
            dishService.save(dish);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateDish(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") LocalDateTime dt_update,
                                        @RequestBody Dish dish) {
        try {
            dishService.update(dish, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            dishService.delete(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
