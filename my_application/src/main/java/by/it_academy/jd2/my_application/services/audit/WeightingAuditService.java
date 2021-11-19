package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.Weighting;
import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.audit.api.IAuditService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IAuditGeneralService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

public class WeightingAuditService implements IAuditService {

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public WeightingAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.WeightingService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Weighting weighting = (Weighting) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(weighting.getCreationDate());
            audit.setText("Создано новое взвешиване " + weighting.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.WEIGHTING);
            audit.setEntityId(weighting.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.WeightingService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Weighting weighting = (Weighting) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(weighting.getCreationDate());
            audit.setText("Изменено взвешиваие " + weighting.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.WEIGHTING);
            audit.setEntityId(weighting.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.WeightingService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Weighting weighting = (Weighting) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(weighting.getCreationDate());
            audit.setText("Удалено взвешивание " + weighting.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.WEIGHTING);
            audit.setEntityId(weighting.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }
}
