package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IWeighMeasurementDao;
import by.it_academy.jd2.my_application.models.WeightMeasurement;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService implements IEntityService<WeightMeasurement> {

    private final IWeighMeasurementDao weightMeasurementDao;

    public MeasurementService(IWeighMeasurementDao weightMeasurementDao) {
        this.weightMeasurementDao = weightMeasurementDao;
    }

    @Override
    public void createEntity(WeightMeasurement weightMeasurement) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        weightMeasurement.setCreationDate(creationDate);
        weightMeasurement.setUpdateDate(creationDate);
        weightMeasurementDao.save(weightMeasurement);
    }

    @Override
    public List<WeightMeasurement> getAllEntities() {
        return weightMeasurementDao.findAll();
    }

    @Override
    public WeightMeasurement getEntity(long id) throws IllegalArgumentException  {
        return weightMeasurementDao.findById(id).orElseThrow();
    }

    @Override
    public void updateEntity(WeightMeasurement weightMeasurement, long id) throws IllegalArgumentException {
        WeightMeasurement updatedWeightMeasurement = getEntity(id);
        updatedWeightMeasurement.setProfile(weightMeasurement.getProfile());
        updatedWeightMeasurement.setWeight(weightMeasurement.getWeight());
        updatedWeightMeasurement.setCreator(weightMeasurement.getCreator());
        updatedWeightMeasurement.setCreationDate(weightMeasurement.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedWeightMeasurement.setUpdateDate(updateDate);

        weightMeasurementDao.save(updatedWeightMeasurement);
    }

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException {
        weightMeasurementDao.deleteById(id);
    }
}
