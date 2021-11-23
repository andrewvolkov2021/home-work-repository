package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.dto.ActiveDto;
import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IActiveService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import by.it_academy.jd2.my_application.utils.ProfileCheck;
import by.it_academy.jd2.my_application.utils.TimeConversion;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/profile")
public class ActiveRestController {

    private final IActiveService activeService;
    private final IProfileService profileService;
    private final TimeConversion timeConversion;
    private final ProfileCheck profileCheck;

    public ActiveRestController(IActiveService activeService, IProfileService profileService,
                                TimeConversion timeConversion, ProfileCheck profileCheck) {

        this.activeService = activeService;
        this.profileService = profileService;
        this.timeConversion = timeConversion;
        this.profileCheck = profileCheck;
    }

    @GetMapping("/{id_profile}/journal/active/")
    public ResponseEntity<?> getActives(@PathVariable("id_Profile") Long idProfile,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                        @RequestParam("dt_start") Long start,
                                        @RequestParam("dt_end") Long end){

        if (profileCheck.checkProfile(idProfile)) {
            try {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                LocalDateTime startTime = timeConversion.conversionTime(start);
                LocalDateTime endTime = timeConversion.conversionTime(end);
                ActiveByDateDto activeByDate = activeService
                        .findAllByProfileIdAndCreationDate(startTime, endTime, idProfile, pageable);
                return new ResponseEntity<>(activeByDate, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id_profile}/journal/active/{id_active}")
    public ResponseEntity<Active> getActive(@PathVariable("id_Profile") Long idProfile,
                                            @PathVariable("id_active") Long idActive) {
        if (profileCheck.checkProfile(idProfile)) {
            try {
                Active exercise = activeService.get(idActive);
                return new ResponseEntity<>(exercise, HttpStatus.OK);
            } catch (IllegalArgumentException  ex) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id_profile}/journal/active")
    public ResponseEntity<?> createActive(@PathVariable("id_Profile") Long idProfile,
                                          @RequestBody ActiveDto activeDto){

        if (profileCheck.checkProfile(idProfile)) {
            try {
                activeDto.setProfile(profileService.get(idProfile));
                activeService.save(activeDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id_profile}/journal/active/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> updateActive(@PathVariable("id_Profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") Long dtUpdate,
                                          @RequestBody ActiveDto activeDto) {

        if (profileCheck.checkProfile(idProfile)) {
            try {
                LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
                activeDto.setProfile(profileService.get(idProfile));
                activeService.update(activeDto, idActive, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id_profile}/journal/active/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteActive(@PathVariable("id_Profile") Long idProfile,
                                          @PathVariable("id_active") Long idActive,
                                          @PathVariable("dt_update") Long dtUpdate) {

        if (profileCheck.checkProfile(idProfile)) {
            try {
                LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
                activeService.delete(idActive, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
