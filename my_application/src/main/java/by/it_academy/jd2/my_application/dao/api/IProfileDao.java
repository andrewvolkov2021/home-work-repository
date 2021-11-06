package by.it_academy.jd2.my_application.dao.api;

import by.it_academy.jd2.my_application.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileDao extends JpaRepository<Profile, Long> {
}
