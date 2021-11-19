package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IWeighingDao;
import by.it_academy.jd2.my_application.models.Weighting;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IWeightingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class WeightingService implements IWeightingService {

    private final IWeighingDao weightingDao;

    public WeightingService(IWeighingDao weightingDao) {
        this.weightingDao = weightingDao;
    }

    @Override
    public void save(Weighting weighting){
        LocalDateTime creationDate = LocalDateTime.now();
        weighting.setCreationDate(creationDate);
        weighting.setUpdateDate(creationDate);
        weightingDao.save(weighting);
    }

    @Override
    public Page<Weighting> getAll(Pageable pageable) {
        return weightingDao.findAll(pageable);
    }

    @Override
    public Weighting get(Long id) throws IllegalArgumentException {
        return weightingDao.findById(id).orElseThrow();
    }

    @Override
    public void update(Weighting weighting, Long id, LocalDateTime dt_update) throws OptimisticLockException {
        Weighting updatedWeighting = get(id);

        if (dt_update != updatedWeighting.getUpdateDate()) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемое взвешивание было изменено");
        } else {
            updatedWeighting.setProfile(weighting.getProfile());
            updatedWeighting.setWeight(weighting.getWeight());
            updatedWeighting.setCreator(weighting.getCreator());
            updatedWeighting.setCreationDate(weighting.getCreationDate());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedWeighting.setUpdateDate(updateDate);

            weightingDao.save(updatedWeighting);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dt_update) throws OptimisticLockException {
        Weighting deletedWeighting = get(id);

        if (dt_update != deletedWeighting.getUpdateDate()) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как" +
                    " удаляемое взвешивание было изменено");
        } else {
            weightingDao.deleteById(id);
        }
    }

    @Override
    public Page<Weighting> findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end,
                                                             Long idWeighting, Pageable pageable) {

        return weightingDao.findAllByCreationDateBetweenAndProfileId(start, end, idWeighting, pageable);
    }
}
