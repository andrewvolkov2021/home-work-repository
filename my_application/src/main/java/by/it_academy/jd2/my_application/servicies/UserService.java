package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IUserDao;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.servicies.api.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getListOfUsers(long page, long size, String name, LocalDateTime begin, LocalDateTime end) {
        return userDao.findAll();
    }

    @Override
    public void createUser(User user) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        user.setCreationDate(creationDate);
        user.setUpdateDate(creationDate);
        userDao.save(user);
    }

    @Override
    public User getUser(long id) throws IllegalArgumentException {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public void updateUser(User user, long id, LocalDateTime dt_update) throws IllegalArgumentException {
        User updatedUser = getUser(id);
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

    @Override
    public void deleteUser(long id, LocalDateTime dt_update) throws IllegalArgumentException {
        userDao.deleteById(id);
    }
}
