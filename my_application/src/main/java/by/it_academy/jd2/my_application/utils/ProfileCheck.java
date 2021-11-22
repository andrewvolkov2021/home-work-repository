package by.it_academy.jd2.my_application.utils;

import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IProfileService;
import org.springframework.stereotype.Component;

@Component
public class ProfileCheck {

    private final UserHolder userHolder;
    private final IProfileService profileService;

    public ProfileCheck(UserHolder userHolder, IProfileService profileService) {
        this.userHolder = userHolder;
        this.profileService = profileService;
    }

    public boolean checkProfile(Long idProfile) {
        try {
            long idUser1 = userHolder.getUser().getId();
            long idUser2 = profileService.get(idProfile).getUser().getId();
            return idUser1 == idUser2;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
