package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IAuditGeneralService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class ProfileAuditService{

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProfileAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.my_application.services.dataBaseService.ProfileService.save(..))", returning = "result")
    public void methodSaveProfile(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Profile profile = (Profile) result;

            Audit audit = new Audit();
            audit.setCreationDate(profile.getCreationDate());
            audit.setText("Создан Profile " + profile.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PROFILE);
            audit.setEntityId(profile.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.ProfileService.update(..))")
    public void methodUpdateProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Profile arg = (Profile) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(arg.getCreationDate());
            audit.setText("Изменен Profile " + arg.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PROFILE);
            audit.setEntityId(arg.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.ProductService.delete(..))")
    public void methodDeleteProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];
            Audit audit = new Audit();
            audit.setCreationDate(LocalDateTime.now().withNano(0));
            audit.setText("Удален Profile " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PROFILE);
            audit.setEntityId(id);
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
