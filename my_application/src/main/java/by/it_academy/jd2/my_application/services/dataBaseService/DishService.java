package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IComponentDao;
import by.it_academy.jd2.my_application.dao.api.IDishDao;
import by.it_academy.jd2.my_application.dto.DishDto;
import by.it_academy.jd2.my_application.models.Component;
import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IDishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishService implements IDishService {

    private final IDishDao dishDao;
    private final IComponentDao componentDao;
    private final UserHolder userHolder;

    public DishService(IDishDao dishDao, IComponentDao componentDao, UserHolder userHolder) {
        this.dishDao = dishDao;
        this.componentDao = componentDao;
        this.userHolder = userHolder;
    }

    @Override
    public Dish save(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setCreator(userHolder.getUser());

        LocalDateTime creationDate = LocalDateTime.now().withNano(0);
        dish.setCreationDate(creationDate);
        dish.setUpdateDate(creationDate);

        List<Component> components = dishDto.getComponents();
        for (Component component : components) {
            component.setCreationDate(creationDate);
            component.setUpdateDate(creationDate);
            componentDao.save(component);
            return dish;
        }
        dishDao.save(dish);
        return dish;
    }

    @Override
    public Page<Dish> getAll(Pageable pageable) {
        return dishDao.findAll(pageable);
    }

    @Override
    public Page<Dish> getAll(String name, Pageable pageable) {
        return dishDao.findRecipeByNameContains(name,pageable);
    }

    @Override
    public Dish get(Long id) throws IllegalArgumentException {
        return dishDao.findById(id).orElseThrow();
    }

    @Override
    public void update(DishDto dishDto, Long id, LocalDateTime dtUpdate) throws OptimisticLockException{
        Dish updatedDish = get(id);
        if (updatedDish.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Невозможно выполнить обновление, так как обновляемое блюдо было изменено");
        } else {

            updatedDish.setName(dishDto.getName());
            updatedDish.setComponents(dishDto.getComponents());
            updatedDish.setCreator(userHolder.getUser());
            updatedDish.setComponents(dishDto.getComponents());

            LocalDateTime updateDate = LocalDateTime.now().withNano(0);
            updatedDish.setUpdateDate(updateDate);

            dishDao.save(updatedDish);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Dish deletedDish = get(id);
        if (deletedDish.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Невозможно выполнить удаление, так как удаляемое блюдо было изменено");
        } else {
            dishDao.deleteById(id);
        }
    }
}

