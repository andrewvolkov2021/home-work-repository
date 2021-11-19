package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IUserDao;
import by.it_academy.jd2.my_application.dto.LoginDto;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        user.setCreationDate(creationDate);
        user.setUpdateDate(creationDate);
        userDao.save(user);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public User get(Long id) throws IllegalArgumentException {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public void update(User user, Long id, LocalDateTime dt_update) throws OptimisticLockException {
        User updatedUser = get(id);
        if (dt_update != updatedUser.getUpdateDate()) {
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
    public void delete(Long id, LocalDateTime dt_update) throws OptimisticLockException {
        User deletedUser = get(id);
        if (dt_update != deletedUser.getUpdateDate()) {
            throw new OptimisticLockException("Удадение не может быть выполнено, така как " +
                    "удаляемый пользователь был изменен");
        } else {
            userDao.deleteById(id);
        }
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
