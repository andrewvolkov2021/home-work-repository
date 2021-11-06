package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IExerciseDao;
import by.it_academy.jd2.my_application.models.Exercise;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseService implements IEntityService<Exercise> {

    private final IExerciseDao exerciseDao;

    public ExerciseService(IExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public void createEntity(Exercise exercise) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        exercise.setCreationDate(creationDate);
        exercise.setUpdateDate(creationDate);
        exerciseDao.save(exercise);
    }

    @Override
    public List<Exercise> getAllEntities() {
        return exerciseDao.findAll();
    }

    @Override
    public Exercise getEntity(long id) throws IllegalArgumentException  {
        return exerciseDao.findById(id).orElseThrow();
    }

    @Override
    public void updateEntity(Exercise exercise, long id) throws IllegalArgumentException {
        Exercise updatedExercise = getEntity(id);
        updatedExercise.setName(exercise.getName());
        updatedExercise.setCalories(exercise.getCalories());
        updatedExercise.setProfile(exercise.getProfile());
        updatedExercise.setCreator(exercise.getCreator());
        updatedExercise.setCreationDate(exercise.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedExercise.setUpdateDate(updateDate);

        exerciseDao.save(updatedExercise);
    }

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException {
        exerciseDao.deleteById(id);
    }
}
