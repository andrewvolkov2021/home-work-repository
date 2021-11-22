package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.JournalByDateDto;
import by.it_academy.jd2.my_application.dto.JournalDto;
import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.services.dataBaseService.ProfileService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IJournalService;
import by.it_academy.jd2.my_application.services.calculations.api.ITimeService;
import by.it_academy.jd2.my_application.utils.ProfileCheck;
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
@RequestMapping("/api/profile")
public class JournalRestController {

    private final IJournalService journalService;
    private final ProfileService profileService;
    private final ITimeService timeService;
    private final TimeConversion timeConversion;
    private final ProfileCheck profileCheck;

    public JournalRestController(IJournalService journalService, ProfileService profileService,
                                 ITimeService timeService, TimeConversion timeConversion, ProfileCheck profileCheck) {
        this.journalService = journalService;
        this.profileService = profileService;
        this.timeService = timeService;
        this.timeConversion = timeConversion;
        this.profileCheck = profileCheck;
    }

    @GetMapping("/{id_profile}/journal/food")
    public ResponseEntity<Page<Journal>> getJournals(@PathVariable("id_profile") long idProfile,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        try {
            if (profileCheck.checkProfile(idProfile)) {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                Page<Journal> journals = journalService.getAllByProfileId(pageable, idProfile);
                return new ResponseEntity<>(journals, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id_profile}/journal/food/byDay/{day}")
    public ResponseEntity<?> getJournalsPerDay(@PathVariable("id_profile") long idProfile,
                                                           @PathVariable("day") int day){
        if (profileCheck.checkProfile(idProfile)){
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
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id_profile}/journal/food/{id_food}")
    public ResponseEntity<?> getJournalCard(@PathVariable("id_profile") long idProfile,
                                            @PathVariable("id_food") long idFood) {

        if (profileCheck.checkProfile(idProfile)){
            try {
                Journal journal = journalService.get(idFood);
                return new ResponseEntity<>(journal, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id_profile}/journal/food")
    public ResponseEntity<?> createJournal(@PathVariable("id_profile") long idProfile,
                                           @RequestBody JournalDto journalDto){

        if (profileCheck.checkProfile(idProfile)) {
            try {
                journalDto.setProfile(profileService.get(idProfile));
                journalService.save(journalDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> updateJournal(@PathVariable("id_profile") long idProfile,
                                           @PathVariable("id_food") Long idFood,
                                           @PathVariable("dt_update") Long dtUpdate,
                                           @RequestBody JournalDto journalDto){

        if (profileCheck.checkProfile(idProfile)){
            try {
                LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
                journalDto.setProfile(profileService.get(idProfile));
                journalService.update(journalDto, idFood, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(value = "/{id_profile}/journal/food/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteJournal(@PathVariable("id_profile") long idProfile,
                                           @PathVariable("id_food") Long id,
                                           @PathVariable("dt_update") Long dtUpdate) {

        if (profileCheck.checkProfile(idProfile)){
            try {
                LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
                journalService.delete(id, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
