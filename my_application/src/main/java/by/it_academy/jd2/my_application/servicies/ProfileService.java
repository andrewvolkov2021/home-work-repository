package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IProfileDao;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.servicies.api.IProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IProfileService {

    private final IProfileDao profileDao;

    public ProfileService(IProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public List<Profile> getListOfProfiles(long page, long size) {
        return profileDao.findAll();
    }

    @Override
    public void createProfile(Profile profile) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        profile.setCreationDate(creationDate);
        profile.setUpdateDate(creationDate);
        profileDao.save(profile);
    }

    @Override
    public Profile getProfile(long id) throws IllegalArgumentException {
        return profileDao.findById(id).orElseThrow();
    }

    @Override
    public void updateProfile(Profile profile, long id, LocalDateTime dt_update) throws IllegalArgumentException {
        Profile updatedProfile = getProfile(id);
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

    @Override
    public void deleteProfile(long id, LocalDateTime dt_update) throws IllegalArgumentException {
        profileDao.deleteById(id);
    }
}
