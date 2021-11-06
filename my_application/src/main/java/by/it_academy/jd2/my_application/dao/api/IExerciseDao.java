package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExerciseDao extends JpaRepository<Exercise, Long> {
}
