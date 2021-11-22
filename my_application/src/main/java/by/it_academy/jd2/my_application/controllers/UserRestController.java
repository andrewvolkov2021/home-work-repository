package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.LoginDto;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
        String token = getJWTToken(loginDto.getLogin());
        try {
            userService.save(loginDto);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(token,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<User> users = userService.getAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512,secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
