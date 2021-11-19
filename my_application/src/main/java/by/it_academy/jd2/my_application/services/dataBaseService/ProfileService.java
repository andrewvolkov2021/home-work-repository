package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IProfileDao;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class ProfileService implements IProfileService {

    private final IProfileDao profileDao;

    public ProfileService(IProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public void save(Profile profile) {
        LocalDateTime creationDate = LocalDateTime.now();
        profile.setCreationDate(creationDate);
        profile.setUpdateDate(creationDate);
        profileDao.save(profile);
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
    public void update(Profile profile, Long id, LocalDateTime dt_update) throws IllegalArgumentException {
        Profile updatedProfile = get(id);

        if (dt_update != updatedProfile.getUpdateDate()) {
            throw new OptimisticLockException("Обновление не может быть выполнено, так как" +
                    " обновляемый профиль был изменен");
        } else {

            updatedProfile.setUser(profile.getUser());
            updatedProfile.setHeight(profile.getHeight());
            updatedProfile.setWeight(profile.getWeight());
            updatedProfile.setBirthdayDate(profile.getBirthdayDate());
            updatedProfile.setSex(profile.getSex());
            updatedProfile.setActivity(profile.getActivity());
            updatedProfile.setTarget(profile.getTarget());
            updatedProfile.setTargetWeight(profile.getTargetWeight());
            updatedProfile.setCreationDate(profile.getCreationDate());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedProfile.setUpdateDate(updateDate);

            profileDao.save(updatedProfile);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dt_update) throws IllegalArgumentException {
        Profile deletedProfile = get(id);

        if (dt_update != deletedProfile.getUpdateDate()) {
            throw new OptimisticLockException("Удаление не может быть выполнено, так как" +
                    " удаляемый профиль был изменен");
        } else {
            profileDao.deleteById(id);
        }
    }
}
