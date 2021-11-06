package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.WeightMeasurement;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weight")
public class MeasurementRestController {

    private final IEntityService<WeightMeasurement> weightMeasurementService;

    public MeasurementRestController(IEntityService<WeightMeasurement> weightMeasurementService){
        this.weightMeasurementService = weightMeasurementService;
    }

    @PostMapping
    public ResponseEntity<?> createWeightMeasurement(@RequestBody WeightMeasurement weightMeasurement){
        try {
            weightMeasurementService.createEntity(weightMeasurement);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateWeightMeasurement(@PathVariable("id") Long id,
                                                     @RequestBody WeightMeasurement weightMeasurement) {
        try {
            weightMeasurementService.updateEntity(weightMeasurement, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteWeightMeasurement(@PathVariable("id") Long id) {
        try {
            weightMeasurementService.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<WeightMeasurement>> getAllWeightMeasurements(@RequestParam("page") int page,
                                                                            @RequestParam("size") int size,
                                                                            @RequestParam("name") String name){
        List<WeightMeasurement> measurements = weightMeasurementService.getAllEntities();
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WeightMeasurement> getWeightMeasurement(@PathVariable("id") Long id) {
        try {
            WeightMeasurement weightMeasurement = weightMeasurementService.getEntity(id);
            return new ResponseEntity<>(weightMeasurement, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
