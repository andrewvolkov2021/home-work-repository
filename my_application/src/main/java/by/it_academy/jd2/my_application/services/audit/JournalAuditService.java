package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.audit.api.IAuditService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IAuditGeneralService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

public class JournalAuditService implements IAuditService {

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public JournalAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.JournalService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Journal journal = (Journal) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(journal.getCreationDate());
            audit.setText("Создан дневник питания " + journal.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.JOURNAL);
            audit.setEntityId(journal.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.JournalService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Journal journal = (Journal) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(journal.getCreationDate());
            audit.setText("Изменен дневник питания " + journal.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.JOURNAL);
            audit.setEntityId(journal.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.JournalService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Journal journal = (Journal) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(journal.getCreationDate());
            audit.setText("Удален дневеик питания " + journal.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.JOURNAL);
            audit.setEntityId(journal.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }
}
