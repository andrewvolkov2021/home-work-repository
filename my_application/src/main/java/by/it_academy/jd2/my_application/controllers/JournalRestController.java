package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.JournalByDateDto;
import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IJournalService;
import by.it_academy.jd2.my_application.services.calculations.api.ITimeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/profile")
public class JournalRestController {

    private final IJournalService journalService;
    private final ITimeService timeService;

    public JournalRestController(IJournalService journalService, ITimeService timeService){
        this.journalService = journalService;
        this.timeService = timeService;
    }

    @GetMapping("/{id_profile}/journal/food")
    public ResponseEntity<Page<Journal>> getJournals(@PathVariable("id_profile") long idProfile,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Journal> journals = journalService.getAllByProfileId(pageable, idProfile);
        //Create
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(value = "/{id_profile}/journal/food/byDay/{day}")
    public ResponseEntity<?> getJournalsPerDay(@PathVariable("id_profile") long idProfile,
                                                           @PathVariable("day") int day){
        try {
            LocalDateTime date = timeService.getDate(day);
            LocalDateTime startOfDate = timeService.detStartOfDate(date);
            LocalDateTime endOfDate = timeService.getEndOfDate(startOfDate);

            JournalByDateDto journalByDay = journalService
                    .findAllByProfileIdAndCreationDate(startOfDate, endOfDate, idProfile);
            return new ResponseEntity<>(journalByDay, HttpStatus.OK);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_profile}/journal/food/{id_food}")
    public ResponseEntity<?> getJournalCard(@PathVariable("id_profile") long idProfile,
                                            @PathVariable("id_food") long idFood) {
        try {
            Journal journal = journalService.get(idFood);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id_profile}/journal/food")
    public ResponseEntity<?> createJournal(@PathVariable("id_profile") long idProfile,
                                           @RequestBody Journal journal){
        try {
            journalService.save(journal);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> updateJournal(@PathVariable("id_profile") long idProfile,
                                           @PathVariable("id_food") Long idFood,
                                           @PathVariable("dt_update") LocalDateTime dt_update,
                                           @RequestBody Journal journal){
        try {
            journalService.update(journal, idFood, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteJournal(@PathVariable("id_profile") long idProfile,
                                           @PathVariable("id_food") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            journalService.delete(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
