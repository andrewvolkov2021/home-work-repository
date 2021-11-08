package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IActiveDao;
import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.servicies.api.IActiveService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActiveService implements IActiveService {

    private final IActiveDao exerciseDao;

    public ActiveService(IActiveDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public void createActive(Active exercise) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        exercise.setCreationDate(creationDate);
        exercise.setUpdateDate(creationDate);
        exerciseDao.save(exercise);
    }

    @Override
    public List<Active> getListOfActives(long page, long size, LocalDateTime start, LocalDateTime end) {
        return exerciseDao.findAll();
    }

    @Override
    public Active getActive(long id) throws IllegalArgumentException  {
        return exerciseDao.findById(id).orElseThrow();
    }

    @Override
    public void updateActive(Active exercise, long id, LocalDateTime dt_update) throws IllegalArgumentException {
        Active updatedExercise = getActive(id);
        updatedExercise.setName(exercise.getName());
        updatedExercise.setCalories(exercise.getCalories());
        updatedExercise.setProfile(exercise.getProfile());
        updatedExercise.setCreationDate(exercise.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedExercise.setUpdateDate(updateDate);

        exerciseDao.save(updatedExercise);
    }

    @Override
    public void deleteActive(long id, LocalDateTime dt_update) throws IllegalArgumentException {
        exerciseDao.deleteById(id);
    }
}
