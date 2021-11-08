package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IWeighingDao;
import by.it_academy.jd2.my_application.models.Weighting;
import by.it_academy.jd2.my_application.servicies.api.IWeightingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeightingService implements IWeightingService {

    private final IWeighingDao weightingDao;

    public WeightingService(IWeighingDao weightingDao) {
        this.weightingDao = weightingDao;
    }

    @Override
    public List<Weighting> getListOfWeightings(long page, long size, LocalDateTime begin, LocalDateTime end) {
        return weightingDao.findAll();
    }

    @Override
    public void createWeighting(Weighting weighting) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        weighting.setCreationDate(creationDate);
        weighting.setUpdateDate(creationDate);
        weightingDao.save(weighting);
    }

    @Override
    public Weighting getWeighting(long id) throws IllegalArgumentException {
        return weightingDao.findById(id).orElseThrow();
    }

    @Override
    public void updateWeighting(Weighting weighting, long id, LocalDateTime dt_update)
            throws IllegalArgumentException {
        Weighting updatedWeighting = getWeighting(id);
        updatedWeighting.setProfile(weighting.getProfile());
        updatedWeighting.setWeight(weighting.getWeight());
        updatedWeighting.setCreator(weighting.getCreator());
        updatedWeighting.setCreationDate(weighting.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedWeighting.setUpdateDate(updateDate);

        weightingDao.save(updatedWeighting);
    }

    @Override
    public void deleteWeighting(long id, LocalDateTime dt_update) throws IllegalArgumentException{
        weightingDao.deleteById(id);
    }
}
