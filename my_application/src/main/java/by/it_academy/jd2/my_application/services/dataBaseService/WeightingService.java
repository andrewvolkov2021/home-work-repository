package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IWeighingDao;
import by.it_academy.jd2.my_application.dto.WeightingByDateDto;
import by.it_academy.jd2.my_application.dto.WeightingDto;
import by.it_academy.jd2.my_application.models.Weighting;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IWeightingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class WeightingService implements IWeightingService {

    private final IWeighingDao weightingDao;
    private final UserHolder userHolder;
    private final IProfileService profileService;

    public WeightingService(IWeighingDao weightingDao, UserHolder userHolder, IProfileService profileService) {
        this.weightingDao = weightingDao;
        this.userHolder = userHolder;
        this.profileService = profileService;
    }

    @Override
    public Weighting save(Long id, WeightingDto weightingDto){
        Weighting weighting = new Weighting();
        weighting.setCreator(userHolder.getUser());
        weighting.setProfile(profileService.get(id));
        weighting.setWeight(weightingDto.getWeight());

        LocalDateTime creationDate = LocalDateTime.now().withNano(0);
        weighting.setCreationDate(creationDate);
        weighting.setUpdateDate(creationDate);
        weightingDao.save(weighting);
        return weighting;
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
    public void update(WeightingDto weightingDto, Long id, Long idProfile, LocalDateTime dtUpdate) throws OptimisticLockException {
        Weighting updatedWeighting = get(id);

        if (!updatedWeighting.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемое взвешивание было изменено");
        } else {

            updatedWeighting.setProfile(profileService.get(idProfile));
            updatedWeighting.setWeight(weightingDto.getWeight());
            updatedWeighting.setCreator(userHolder.getUser());

            LocalDateTime updateDate = LocalDateTime.now().withNano(0);
            updatedWeighting.setUpdateDate(updateDate);

            weightingDao.save(updatedWeighting);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Weighting deletedWeighting = get(id);

        if (deletedWeighting.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как" +
                    " удаляемое взвешивание было изменено");
        } else {
            weightingDao.deleteById(id);
        }
    }

    @Override
    public WeightingByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end,
                                                                Long id, Pageable pageable) {
        WeightingByDateDto weightingByDateDto = new WeightingByDateDto();
        Page<Weighting> weightings = weightingDao
                .findAllByCreationDateBetweenAndProfileId(start, end, id, pageable);
        weightingByDateDto.setWeightings(weightings);
        return weightingByDateDto;
    }
}
