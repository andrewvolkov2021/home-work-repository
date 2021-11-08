package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.servicies.api.IJournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/food")
public class JournalRestController {

    private final IJournalService journalService;

    public JournalRestController(IJournalService journalService){
        this.journalService = journalService;
    }

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody Journal journal){
        try {
            journalService.createJournal(journal);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> updateJournal(@PathVariable("id_food") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update,
                                           @RequestBody Journal journal){
        try {
            journalService.updateJournal(journal, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteJournal(@PathVariable("id_food") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            journalService.deleteJournal(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournals(@RequestParam("page") int page,
                                                        @RequestParam("size") int size){
        List<Journal> journals = journalService.getListOfJournals(page, size);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(value = "/{day}")
    public ResponseEntity<List<Journal>> getJournalsPerDay(@PathVariable("day") LocalDateTime day){
        List<Journal> journalsByDay =  journalService.getListOfJournalsFPerDay(day);
        return new ResponseEntity<>(journalsByDay, HttpStatus.OK);
    }

    @GetMapping(value = "/{id_food}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id_food") Long id) {
        try {
            Journal journal = journalService.getJournal(id);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
