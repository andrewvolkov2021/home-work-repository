package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IProfileDao;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IEntityService<Profile> {

    private final IProfileDao profileDao;

    public ProfileService(IProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public void createEntity(Profile profile) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        profile.setCreationDate(creationDate);
        profile.setUpdateDate(creationDate);
        profileDao.save(profile);
    }

    @Override
    public List<Profile> getAllEntities() {
        return profileDao.findAll();
    }

    @Override
    public Profile getEntity(long id) throws IllegalArgumentException  {
        return profileDao.findById(id).orElseThrow();
    }

    @Override
    public void updateEntity(Profile profile, long id) throws IllegalArgumentException {
        Profile updatedProfile = getEntity(id);
        updatedProfile.setUser(profile.getUser());
        updatedProfile.setHeight(profile.getHeight());
        updatedProfile.setWeight(profile.getWeight());
        updatedProfile.setBirthdayDate(profile.getBirthdayDate());
        updatedProfile.setSex(profile.getSex());
        updatedProfile.setActivity(profile.getActivity());
        updatedProfile.setTarget(profile.getTarget());
        updatedProfile.setCreator(profile.getCreator());
        updatedProfile.setCreationDate(profile.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedProfile.setUpdateDate(updateDate);

        profileDao.save(updatedProfile);
    }

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException {
        profileDao.deleteById(id);
    }
}
