package by.it_academy.jd2.my_application.controllers;

import by.it_academy.jd2.my_application.dto.LoginDto;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.security.JwtProvider;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final IUserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(IUserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid LoginDto loginDto) {
        userService.save(loginDto);
        return "OK";
    }

    @PostMapping("/auth")
    public String authUser(@RequestBody LoginDto loginDto) {
        User userEntity = userService.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
        String token =jwtProvider.generateToken(userEntity.getLogin());
        return token;
    }
}
