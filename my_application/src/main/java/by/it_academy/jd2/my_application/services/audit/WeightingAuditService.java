package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.Weighting;
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
public class WeightingAuditService{

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public WeightingAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.my_application.services.dataBaseService.WeightingService.save(..))", returning = "result")
    public void methodSaveWeighting(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Weighting weighting = (Weighting) result;

            Audit audit = new Audit();
            audit.setCreationDate(weighting.getCreationDate());
            audit.setText("Создан Weighting " + weighting.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.WEIGHTING);
            audit.setEntityId(weighting.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.WeightingService.update(..))")
    public void methodUpdateWeighting(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Weighting arg = (Weighting) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(arg.getCreationDate());
            audit.setText("Изменен Weighting " + arg.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.WEIGHTING);
            audit.setEntityId(arg.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.WeightingService.delete(..))")
    public void methodDeleteWeighting(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];
            Audit audit = new Audit();
            audit.setCreationDate(LocalDateTime.now().withNano(0));
            audit.setText("Удален Weighting " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.WEIGHTING);
            audit.setEntityId(id);
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
