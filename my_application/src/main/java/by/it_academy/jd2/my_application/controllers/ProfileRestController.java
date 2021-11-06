package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileRestController {

    private final IEntityService<Profile> profileService;

    public ProfileRestController(IEntityService<Profile> profileService){
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody Profile profile){
        try {
            profileService.createEntity(profile);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") Long id,
                                           @RequestBody Profile profile) {
        try {
            profileService.updateEntity(profile, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Long id) {
        try {
            profileService.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("name") String name){
        List<Profile> profiles = profileService.getAllEntities();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable("id") Long id) {
        try {
            Profile profile = profileService.getEntity(id);
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
