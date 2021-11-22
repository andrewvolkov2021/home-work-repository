package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.LoginDto;
import by.it_academy.jd2.my_application.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IUserService {

    User findByLogin(String login);

    void save(LoginDto loginDto);

    Page<User> getAll (Pageable pageable);

    User get(Long id);

    User findByLoginAndPassword(String login, String password);

    void update(User user, Long id, LocalDateTime dtUpdate);

    void delete(Long id, LocalDateTime dtUpdate);
}
