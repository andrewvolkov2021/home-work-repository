package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IActiveDao;
import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.services.calculations.api.IActiveByDateService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IActiveService;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActiveService implements IActiveService {

    private final IActiveDao activeDao;
    private final IActiveByDateService activeByDateService;

    public ActiveService(IActiveDao activeDao, IActiveByDateService activeByDateService) {
        this.activeDao = activeDao;
        this.activeByDateService = activeByDateService;
    }

    @Override
    public void save(Active active) {
        LocalDateTime creationDate = LocalDateTime.now();
        active.setCreationDate(creationDate);
        active.setUpdateDate(creationDate);
        activeDao.save(active);
    }

    @Override
    public ActiveByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end, Long id) {

        List<Active> actives = activeDao.findAllByCreationDateBetweenAndProfileId(start, end, id);
            return activeByDateService.getActiveByDate(actives);
    }

    @Override
    public Active get(Long id) throws IllegalArgumentException  {
        return activeDao.findById(id).orElseThrow();
    }

    @Override
    public void update(Active exercise, Long id, LocalDateTime dt_update) throws OptimisticLockException {
        Active updatedActive = get(id);

        if (dt_update != updatedActive.getUpdateDate()) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемая активность была изменена");
        } else {

            updatedActive.setName(exercise.getName());
            updatedActive.setCalories(exercise.getCalories());
            updatedActive.setProfile(exercise.getProfile());
            updatedActive.setCreationDate(exercise.getCreationDate());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedActive.setUpdateDate(updateDate);

            activeDao.save(updatedActive);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dt_update) throws OptimisticLockException {
        Active deletedActive = get(id);
        if (dt_update != deletedActive.getUpdateDate()) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как удаляемая " +
                    "активность была изменена");
        } else {
            activeDao.deleteById(id);
        }
    }
}
