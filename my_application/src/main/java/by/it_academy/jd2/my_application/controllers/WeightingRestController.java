package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.WeightingByDateDto;
import by.it_academy.jd2.my_application.dto.WeightingDto;
import by.it_academy.jd2.my_application.models.Weighting;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IWeightingService;
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
@RequestMapping("/api/profile")
public class WeightingRestController {

    private final IWeightingService weightingService;
    private final IProfileService profileService;
    private final TimeConversion timeConversion;
    private final ProfileCheck profileCheck;

    public WeightingRestController(IWeightingService weightingService, IProfileService profileService,
                                   TimeConversion timeConversion, ProfileCheck profileCheck) {
        this.weightingService = weightingService;
        this.profileService = profileService;
        this.timeConversion = timeConversion;
        this.profileCheck = profileCheck;
    }

    @GetMapping("/{id_profile}/journal/weight")
    public ResponseEntity<?> getWeightings(@PathVariable("id_profile") Long idProfile,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                                         @RequestParam("dt_start") Long start,
                                                         @RequestParam("dt_end") Long end){

        if (profileCheck.checkProfile(idProfile)){
            try {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
                LocalDateTime startTime = timeConversion.conversionTime(start);
                LocalDateTime endTime = timeConversion.conversionTime(end);

                WeightingByDateDto weightingByDateDto = weightingService
                        .findAllByProfileIdAndCreationDate(startTime, endTime, idProfile, pageable);
                return new ResponseEntity<>(weightingByDateDto, HttpStatus.OK);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id_profile}/journal/weight/{id_weight}")
    public ResponseEntity<Weighting> getWeighting(@PathVariable("id_profile") Long idProfile,
                                                  @PathVariable("id_weight") Long idWeight) {

        if (profileCheck.checkProfile(idProfile)){
            try {
                Weighting weighting = weightingService.get(idWeight);
                return new ResponseEntity<>(weighting, HttpStatus.OK);
            } catch (IllegalArgumentException  ex) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{id_profile}/journal/weight")
    public ResponseEntity<?> createWeighting(@PathVariable("id_profile") Long idProfile,
                                             @RequestBody WeightingDto weightingDto){

        if (profileCheck.checkProfile(idProfile)){
            try {
                weightingService.save(idProfile, weightingDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id_profile}/journal/weight/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> updateWeighting(@PathVariable("id_profile") Long idProfile,
                                             @PathVariable("id_weight") Long idWeighting,
                                             @PathVariable("dt_update") Long dtUpdate,
                                             @RequestBody WeightingDto weightingDto) {

        if (profileCheck.checkProfile(idProfile)){
            try {
                LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
                weightingService.update(weightingDto, idWeighting, idProfile, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (OptimisticLockException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id_profile}/journal/weight/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteWeighting(@PathVariable("id_profile") Long idProfile,
                                             @PathVariable("id_weight") Long idWeighting,
                                             @PathVariable("dt_update") Long dtUpdate) {

        if (profileCheck.checkProfile(idProfile)){
            try {
                LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
                weightingService.delete(idWeighting, dtUpdateTime);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
