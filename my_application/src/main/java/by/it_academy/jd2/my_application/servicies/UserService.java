package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IUserDao;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IEntityService<User> {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createEntity(User user) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        user.setCreationDate(creationDate);
        user.setUpdateDate(creationDate);
        userDao.save(user);
    }

    @Override
    public List<User> getAllEntities() {
        return userDao.findAll();
    }

    @Override
    public User getEntity(long id) throws IllegalArgumentException  {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public void updateEntity(User user, long id) throws IllegalArgumentException {
        User updatedUser = getEntity(id);
        updatedUser.setName(user.getName());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRole(user.getRole());
        updatedUser.setCreator(user.getCreator());
        updatedUser.setCreationDate(user.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedUser.setUpdateDate(updateDate);

        userDao.save(updatedUser);
    }

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException {
        userDao.deleteById(id);
    }
}
