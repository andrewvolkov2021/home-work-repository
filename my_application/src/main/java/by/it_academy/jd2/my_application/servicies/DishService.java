package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IDishDao;
import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.servicies.api.IDishService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishService implements IDishService {

    private final IDishDao dishDao;

    public DishService(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public void createDish(Dish dish) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        dish.setCreationDate(creationDate);
        dish.setUpdateDate(creationDate);
        dishDao.save(dish);
    }

    @Override
    public List<Dish> getListOfDishes(long page, long size, String name) {
        return dishDao.findAll();
    }

    @Override
    public Dish getDish(long id) throws IllegalArgumentException {
        return dishDao.findById(id).orElse(null);
    }

    @Override
    public void updateDish(Dish dish, long id, LocalDateTime dt_update) throws IllegalArgumentException{
        Dish updatedDish = getDish(id);
        updatedDish.setName(dish.getName());
        updatedDish.setComponents(dish.getComponents());
        updatedDish.setCreator(dish.getCreator());
        updatedDish.setCreationDate(dish.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedDish.setUpdateDate(updateDate);

        dishDao.save(updatedDish);
    }

    @Override
    public void deleteDish(long id, LocalDateTime dt_update) throws IllegalArgumentException {
        dishDao.deleteById(id);
    }
}
