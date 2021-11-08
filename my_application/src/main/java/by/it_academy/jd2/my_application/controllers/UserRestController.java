package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.servicies.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final IUserService userService;

    public UserRestController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") LocalDateTime dt_update,
                                        @RequestBody User user) {
        try {
            userService.updateUser(user, id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") LocalDateTime dt_update) {
        try {
            userService.deleteUser(id, dt_update);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam("page") int page,
                                                  @RequestParam("size") int size,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("dt_start") LocalDateTime dt_start,
                                                  @RequestParam("dt_end") LocalDateTime dt_end){
        List<User> users = userService.getListOfUsers(page, size, name, dt_start, dt_end);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException  ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
