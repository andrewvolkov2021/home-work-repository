package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food_diary")
public class JournalRestController {

    private final IEntityService<Journal> diaryService;

    @Autowired
    public JournalRestController(IEntityService<Journal> diaryService){
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody Journal journal){
        try {
            diaryService.createEntity(journal);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateJournal(@PathVariable("id") Long id,
                                           @RequestBody Journal journal){
        try {
            diaryService.updateEntity(journal, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable("id") Long id) {
        try {
            diaryService.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournals(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("name") String name){
        List<Journal> journals = diaryService.getAllEntities();
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id") Long id) {
        try {
            Journal journal = diaryService.getEntity(id);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
