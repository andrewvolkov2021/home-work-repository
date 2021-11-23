package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IUserDao;
import by.it_academy.jd2.my_application.dto.LoginDto;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.api.ERole;
import by.it_academy.jd2.my_application.models.api.EStatus;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final IProfileService profileService;

    public UserService(IUserDao userDao, PasswordEncoder passwordEncoder, IProfileService profileService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.profileService = profileService;
    }

    @Override
    public void save(LoginDto loginDto) {
        User user = new User();
        user.setLogin(loginDto.getLogin());
        user.setName(loginDto.getName());
        user.setRole(ERole.ROLE_USER);
        user.setStatus(EStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));

        LocalDateTime creationDate = LocalDateTime.now().withNano(0);
        user.setCreationDate(creationDate);
        user.setUpdateDate(creationDate);
        userDao.save(user);

        Profile profile = new Profile();
        profile.setUser(user);
        profileService.save(profile);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public User get(Long id) throws IllegalArgumentException {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public void update(User user, Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        User updatedUser = get(id);
        if (dtUpdate != updatedUser.getUpdateDate()) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как " +
                    "обновляемый пользователь был изменен");
        } else {
            updatedUser.setName(user.getName());
            updatedUser.setLogin(user.getLogin());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            updatedUser.setStatus(user.getStatus());
            updatedUser.setCreationDate(user.getCreationDate());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedUser.setUpdateDate(updateDate);

            userDao.save(updatedUser);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        User deletedUser = get(id);
        Profile profile = profileService.findByUser(deletedUser);
        if (dtUpdate != deletedUser.getUpdateDate()) {
            throw new OptimisticLockException("Удадение не может быть выполнено, така как " +
                    "удаляемый пользователь был изменен");
        } else {
            profileService.delete(profile.getId(), dtUpdate);
            userDao.deleteById(id);
        }
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = userDao.findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void activateUser(Long id) {
        User user = get(id);
        user.setStatus(EStatus.ACTIVE);
        user.setUpdateDate(LocalDateTime.now());
        userDao.saveAndFlush(user);
    }
}
