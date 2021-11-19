package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.audit.api.IAuditService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IAuditGeneralService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

public class ActiveAuditService implements IAuditService {

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ActiveAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.ActiveService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Active active = (Active) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(active.getCreationDate());
            audit.setText("Создана активность " + active.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.ACTIVE);
            audit.setEntityId(active.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.ActiveService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Active active = (Active) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(active.getCreationDate());
            audit.setText("Изменена активность " + active.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.ACTIVE);
            audit.setEntityId(active.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.ActiveService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Active active = (Active) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(active.getCreationDate());
            audit.setText("Удалена активность " + active.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PRODUCT);
            audit.setEntityId(active.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }
}
