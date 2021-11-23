package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.ProfileDto;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import by.it_academy.jd2.my_application.utils.TimeConversion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {

    private final IProfileService profileService;
    private final TimeConversion timeConversion;

    public ProfileRestController(IProfileService profileService, TimeConversion timeConversion){
        this.profileService = profileService;
        this.timeConversion = timeConversion;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable("id") Long id) {
        try {
            Profile profile = profileService.get(id);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") Long dtUpdate,
                                           @RequestBody ProfileDto profileDto) {
        try {
            LocalDateTime dtUpdateTime = timeConversion.conversionTime(dtUpdate);
            profileService.update(profileDto, id, dtUpdateTime);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OptimisticLockException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
