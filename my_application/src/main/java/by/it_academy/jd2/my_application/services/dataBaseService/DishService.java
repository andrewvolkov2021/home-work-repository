package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IDishDao;
import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IDishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class DishService implements IDishService {

    private final IDishDao dishDao;

    public DishService(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public void save(Dish dish) {
        LocalDateTime creationDate = LocalDateTime.now();
        dish.setCreationDate(creationDate);
        dish.setUpdateDate(creationDate);
        dishDao.save(dish);
    }

    @Override
    public Page<Dish> getAll(Pageable pageable) {
        return dishDao.findAll(pageable);
    }

    @Override
    public Dish get(Long id) throws IllegalArgumentException {
        return dishDao.findById(id).orElseThrow();
    }

    @Override
    public void update(Dish dish, Long id, LocalDateTime dt_update) throws IllegalArgumentException{
        Dish updatedDish = get(id);
        if (dt_update != updatedDish.getUpdateDate()) {
            throw new OptimisticLockException("Невозможно выполнить обновление, так как обновляемое блюдо было изменено");
        } else {

            updatedDish.setName(dish.getName());
            updatedDish.setComponents(dish.getComponents());
            updatedDish.setCreator(dish.getCreator());
            updatedDish.setCreationDate(dish.getCreationDate());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedDish.setUpdateDate(updateDate);

            dishDao.save(updatedDish);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dt_update) throws IllegalArgumentException {
        Dish deletedDish = get(id);
        if (dt_update != deletedDish.getUpdateDate()) {
            throw new OptimisticLockException("Невозможно выполнить удаление, так как удаляемое блюдо было изменено");
        } else {
            dishDao.deleteById(id);
        }
    }
}
