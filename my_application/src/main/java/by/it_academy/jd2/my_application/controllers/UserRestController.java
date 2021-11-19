package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.LoginDto;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public class UserController {

        private final IUserService userService;
        private PasswordEncoder passwordEncoder;

        public UserController(IUserService userService, PasswordEncoder passwordEncoder) {
            this.userService = userService;
            this.passwordEncoder = passwordEncoder;
        }

        @PostMapping()
        public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
            String token = getJWTToken(loginDto.getLogin());
            User user = new User();
            user.setLogin(loginDto.getLogin());
            user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
            userService.save(user);
            return new ResponseEntity<>(token, HttpStatus.CREATED);
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
                    .signWith(SignatureAlgorithm.HS512,
                            secretKey.getBytes()).compact();

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
}
