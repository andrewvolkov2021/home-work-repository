package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IProfileDao;
import by.it_academy.jd2.my_application.dto.ProfileDto;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import by.it_academy.jd2.my_application.utils.TimeConversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class ProfileService implements IProfileService {

    private final IProfileDao profileDao;
    private final TimeConversion timeConversion;

    public ProfileService(IProfileDao profileDao, TimeConversion timeConversion) {
        this.profileDao = profileDao;
        this.timeConversion = timeConversion;
    }

    @Override
    public Profile save(Profile profile) {
        LocalDateTime creationDate = LocalDateTime.now().withNano(0);
        profile.setCreationDate(creationDate);
        profile.setUpdateDate(creationDate);
        profileDao.save(profile);
        return profile;
    }

    @Override
    public Page<Profile> getAll(Pageable pageable) {
        return profileDao.findAll(pageable);
    }

    @Override
    public Profile get(Long id) throws IllegalArgumentException {
        return profileDao.findById(id).orElseThrow();
    }

    @Override
    public void update(ProfileDto profileDto, Long id, LocalDateTime dtUpdate) throws IllegalArgumentException {
        Profile updatedProfile = get(id);
        LocalDateTime updateTime = updatedProfile.getUpdateDate();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        updateTime.truncatedTo(ChronoUnit.SECONDS).format(dtf);


        if (!updateTime.isEqual(dtUpdate)) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемый профиль был изменен");
        } else {

            updatedProfile.setUser(profileDto.getUser());
            updatedProfile.setHeight(profileDto.getHeight());
            updatedProfile.setWeight(profileDto.getWeight());
            updatedProfile.setBirthdayDate(timeConversion.conversionTime(profileDto.getBirthdayDate()));
            updatedProfile.setSex(profileDto.getSex());
            updatedProfile.setActivity(profileDto.getActivity());
            updatedProfile.setTarget(profileDto.getTarget());
            updatedProfile.setTargetWeight(profileDto.getTargetWeight());

            LocalDateTime updateDate = LocalDateTime.now().withNano(0);
            updatedProfile.setUpdateDate(updateDate);

            profileDao.save(updatedProfile);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws IllegalArgumentException {
        Profile deletedProfile = get(id);

        if (dtUpdate != deletedProfile.getUpdateDate()) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как" +
                    " удаляемый профиль был изменен");
        } else {
            profileDao.deleteById(id);
        }
    }

    @Override
    public Profile findByUser(User user) {
        return profileDao.findByUser(user);
    }
}
