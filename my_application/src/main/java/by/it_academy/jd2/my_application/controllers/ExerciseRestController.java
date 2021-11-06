package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Exercise;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseRestController {

    private final IEntityService<Exercise> exerciseService;

    public ExerciseRestController(IEntityService<Exercise> exerciseService){
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise){
        try {
            exerciseService.createEntity(exercise);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable("id") Long id,
                                           @RequestBody Exercise exercise) {
        try {
            exerciseService.updateEntity(exercise, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable("id") Long id) {
        try {
            exerciseService.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("name") String name){
        List<Exercise> exercises = exerciseService.getAllEntities();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable("id") Long id) {
        try {
            Exercise exercise = exerciseService.getEntity(id);
            return new ResponseEntity<>(exercise, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
