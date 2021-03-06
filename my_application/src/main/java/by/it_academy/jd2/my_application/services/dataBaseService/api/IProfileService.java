package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.dto.ProfileDto;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IProfileService {

    Profile save(Profile profile);

    Page<Profile> getAll(Pageable pageable);

    Profile get(Long id);

    void update(ProfileDto profileDto, Long id, LocalDateTime dt_update);

    void delete (Long id, LocalDateTime dt_update);

    Profile findByUser(User user);
}
