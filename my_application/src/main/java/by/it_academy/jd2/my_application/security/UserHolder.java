package by.it_academy.jd2.my_application.security;

import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserHolder {
    private final IUserService userService;

    public UserHolder(IUserService userService) {
        this.userService = userService;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getUser() {
        String login = getAuthentication().getName();
        return userService.findByLogin(login);
    }
}
