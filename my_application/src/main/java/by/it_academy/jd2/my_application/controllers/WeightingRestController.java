package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Weighting;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IWeightingService;
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
@RequestMapping("/api/profile")
public class WeightingRestController {

    private final IWeightingService weightingService;

    public WeightingRestController(IWeightingService weightingService){
        this.weightingService = weightingService;
    }

    @GetMapping("/{id_profile}/journal/weight/")
    public ResponseEntity<Page<Weighting>> getWeightings(@PathVariable("id_profile") Long idProfile,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                                         @RequestParam("dt_start") LocalDateTime start,
                                                         @RequestParam("dt_end") LocalDateTime end){

        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
            Page<Weighting> measurementPage = weightingService
                    .findAllByProfileIdAndCreationDate(start, end, idProfile, pageable);
            return new ResponseEntity<>(measurementPage, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_profile}/journal/weight/{id_weight}")
    public ResponseEntity<Weighting> getWeighting(@PathVariable("id_profile") Long idProfile,
                                                  @PathVariable("id_weight") Long idWeight) {
        try {
            Weighting weighting = weightingService.get(idWeight);
            return new ResponseEntity<>(weighting, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id_profile}/journal/weight")
    public ResponseEntity<?> createWeighting(@PathVariable("id_profile") Long idProfile,
                                             @RequestBody Weighting weighting){

        try {
            weightingService.save(weighting);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{id_profile}/journal/weight/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> updateWeighting(@PathVariable("id_profile") Long idProfile,
                                             @PathVariable("id_weight") Long idWeighting,
                                             @PathVariable("dt_update") LocalDateTime dtUpdate,
                                             @RequestBody Weighting weighting) {
        try {
            weightingService.update(weighting, idWeighting, dtUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id_profile}/journal/weight/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteWeighting(@PathVariable("id_profile") Long idProfile,
                                             @PathVariable("id_weight") Long idWeighting,
                                             @PathVariable("dt_update") LocalDateTime dtUpdate) {
        try {
            weightingService.delete(idWeighting, dtUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
