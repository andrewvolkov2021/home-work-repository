package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.servicies.api.IActiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/active")
public class ActiveRestController {

    private final IActiveService activeService;

    public ActiveRestController(IActiveService activeService){
        this.activeService = activeService;
    }

    @PostMapping
    public ResponseEntity<?> createActive(@RequestBody Active active){
        try {
            activeService.createActive(active);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> updateActive(@PathVariable("id") Long id,
                                          @PathVariable("dt_update") LocalDateTime dt_update,
                                          @RequestBody Active active) {
        try {
            activeService.updateActive(active, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteActive(@PathVariable("id") Long id,
                                          @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            activeService.deleteActive(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Active>> getListOfActives(@RequestParam("page") int page,
                                                         @RequestParam("size") int size,
                                                         @RequestParam("dt_start") LocalDateTime dt_start,
                                                         @RequestParam("dt_end") LocalDateTime dt_end){
        List<Active> actives = activeService.getListOfActives(page, size, dt_start, dt_end);
        return new ResponseEntity<>(actives, HttpStatus.OK);
    }

    @GetMapping(value = "/{id_active}")
    public ResponseEntity<Active> getActive(@PathVariable("id_active") Long id) {
        try {
            Active exercise = activeService.getActive(id);
            return new ResponseEntity<>(exercise, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
