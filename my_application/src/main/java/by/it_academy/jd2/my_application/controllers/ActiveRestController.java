package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IActiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/profile")
public class ActiveRestController {

    private final IActiveService activeService;

    public ActiveRestController(IActiveService activeService){
        this.activeService = activeService;
    }


    @GetMapping("/{id_profile}/journal/active/")
    public ResponseEntity<?> getActives(@PathVariable("id_Profile") Long idProfile,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                        @RequestParam("dt_start") LocalDateTime start,
                                        @RequestParam("dt_end") LocalDateTime end){

        try {
            ActiveByDateDto activeByDate = activeService
                    .findAllByProfileIdAndCreationDate(start, end, idProfile);
            return new ResponseEntity<>(activeByDate, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_profile}/journal/active/{id_active}")
    public ResponseEntity<Active> getActive(@PathVariable("id_Profile") Long idProfile,
                                            @PathVariable("id_active") Long idActive) {
        try {
            Active exercise = activeService.get(idActive);
            return new ResponseEntity<>(exercise, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id_profile}/journal/active")
    public ResponseEntity<?> createActive(@PathVariable("id_Profile") Long idProfile,
                                          @RequestBody Active active){

        try {
            activeService.save(active);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{id_profile}/journal/active/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> updateActive(@PathVariable("id_Profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") LocalDateTime dtUpdate,
                                          @RequestBody Active active) {

        try {
            activeService.update(active, idActive, dtUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id_profile}/journal/active/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteActive(@PathVariable("id_Profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") LocalDateTime dtUpdate) {
        try {
            activeService.delete(idActive, dtUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
