package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.api.ERole;
import by.it_academy.jd2.my_application.models.api.EStatus;

public class UserDto {

    private String name;
    private String login;
    private String password;
    private ERole role;
    private EStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
