package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final IEntityService<User> userService;

    public UserRestController(IEntityService<User> userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            userService.createEntity(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                        @RequestBody User user) {
        try {
            userService.updateEntity(user, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam("page") int page,
                                                  @RequestParam("size") int size,
                                                  @RequestParam("name") String name){
        List<User> users = userService.getAllEntities();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        try {
            User user = userService.getEntity(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
