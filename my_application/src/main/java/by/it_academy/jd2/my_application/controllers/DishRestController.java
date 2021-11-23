package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.DishDto;
import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IDishService;
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
@RequestMapping("/api/recipe")
public class DishRestController {

    private final IDishService dishService;
    private final TimeConversion timeConversion;

    public DishRestController(IDishService dishService, TimeConversion timeConversion){
        this.dishService = dishService;
        this.timeConversion = timeConversion;
    }

    @GetMapping
    public ResponseEntity<Page<Dish>> getAllDishes(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                   @RequestParam(value = "name", required = false) String name){

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Dish> dishes;
        if (name != null) {
            dishes = dishService.getAll(name, pageable);
        } else {
            dishes = dishService.getAll(pageable);
        }
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
    public ResponseEntity<?> createDish(@RequestBody DishDto dishDto){
        try {
            dishService.save(dishDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateDish(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") Long dtUpdate,
                                        @RequestBody DishDto dishDto) {
        try {
            LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
            dishService.update(dishDto, id, dtUpdateTime);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") Long dtUpdate) {
        try {
            LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
            dishService.delete(id, dtUpdateTime);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
