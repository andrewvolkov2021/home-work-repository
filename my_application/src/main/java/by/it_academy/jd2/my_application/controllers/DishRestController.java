package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class DishRestController {

    private final IEntityService<Dish> dishService;

    @Autowired
    public DishRestController(IEntityService<Dish> dishService){
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<?> createDish(@RequestBody Dish dish){
        try {
            dishService.createEntity(dish);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDish(@PathVariable("id") Long id,
                                        @RequestBody Dish dish) {
        try {
            dishService.updateEntity(dish, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id) {
        try {
            dishService.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes(@RequestParam("page") int page,
                                                   @RequestParam("size") int size,
                                                   @RequestParam("name") String name){
        List<Dish> dishes = dishService.getAllEntities();
        return new ResponseEntity<>(dishes, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dish> getDish(@PathVariable("id") Long id) {
        try {
            Dish dish = dishService.getEntity(id);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
