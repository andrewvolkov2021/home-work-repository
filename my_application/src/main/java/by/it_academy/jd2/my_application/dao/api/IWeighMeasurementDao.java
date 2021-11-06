package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.WeightMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeighMeasurementDao extends JpaRepository<WeightMeasurement, Long> {
}
