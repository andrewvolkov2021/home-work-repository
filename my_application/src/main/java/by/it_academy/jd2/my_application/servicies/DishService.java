package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IDishDao;
import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishService implements IEntityService<Dish> {

    private final IDishDao dishDao;

    public DishService(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public void createEntity(Dish dish) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        dish.setCreationDate(creationDate);
        dish.setUpdateDate(creationDate);
        dishDao.save(dish);
    }

    @Override
    public List<Dish> getAllEntities() {
        return dishDao.findAll();
    }

    @Override
    public Dish getEntity(long id) throws IllegalArgumentException {
        return dishDao.findById(id).orElse(null);
    }

    @Override
    public void updateEntity(Dish dish, long id) throws IllegalArgumentException{
        Dish updatedDish = getEntity(id);
        updatedDish.setName(dish.getName());
        updatedDish.setComponents(dish.getComponents());
        updatedDish.setCreator(dish.getCreator());
        updatedDish.setCreationDate(dish.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedDish.setUpdateDate(updateDate);

        dishDao.save(updatedDish);
    }

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException {
        dishDao.deleteById(id);
    }
}
