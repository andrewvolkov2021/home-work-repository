package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Weighting;
import by.it_academy.jd2.my_application.servicies.api.IWeightingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/weight")
public class WeightingRestController {

    private final IWeightingService weightingService;

    public WeightingRestController(IWeightingService weightingService){
        this.weightingService = weightingService;
    }

    @PostMapping
    public ResponseEntity<?> createWeighting(@RequestBody Weighting weighting){
        try {
            weightingService.createWeighting(weighting);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> updateWeighting(@PathVariable("id_weight") Long id,
                                                     @PathVariable("dt_update") LocalDateTime dt_update,
                                                     @RequestBody Weighting weighting) {
        try {
            weightingService.updateWeighting(weighting, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteWeighting(@PathVariable("id") Long id,
                                             @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            weightingService.deleteWeighting(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Weighting>> getListOfWeighting(@RequestParam("page") int page,
                                                              @RequestParam("size") int size,
                                                              @RequestParam("dt_start") LocalDateTime start,
                                                              @RequestParam("dt_end") LocalDateTime end){
        List<Weighting> weightings = weightingService.getListOfWeightings(page, size, start, end);
        return new ResponseEntity<>(weightings, HttpStatus.OK);
    }

    @GetMapping(value = "/{id_weight}")
    public ResponseEntity<Weighting> getWeighting(@PathVariable("id_weight") Long id) {
        try {
            Weighting weighting = weightingService.getWeighting(id);
            return new ResponseEntity<>(weighting, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
