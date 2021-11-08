package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.servicies.api.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class DishRestController {

    private final IDishService dishService;

    @Autowired
    public DishRestController(IDishService dishService){
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<?> createDish(@RequestBody Dish dish){
        try {
            dishService.createDish(dish);
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
            dishService.updateDish(dish, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            dishService.deleteDish(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes(@RequestParam("page") int page,
                                                   @RequestParam("size") int size,
                                                   @RequestParam("name") String name){
        List<Dish> dishes = dishService.getListOfDishes(page, size, name);
        return new ResponseEntity<>(dishes, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dish> getDish(@PathVariable("id") Long id) {
        try {
            Dish dish = dishService.getDish(id);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
