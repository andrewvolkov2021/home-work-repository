package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.audit.api.IAuditService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IAuditGeneralService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

public class DishAuditService implements IAuditService {

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public DishAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.DishService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Dish dish = (Dish) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(dish.getCreationDate());
            audit.setText("Создано блюдо " + dish.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.DISH);
            audit.setEntityId(dish.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.DishService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Dish dish = (Dish) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(dish.getCreationDate());
            audit.setText("Изменено блюдо " + dish.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.DISH);
            audit.setEntityId(dish.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.DishService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Dish dish = (Dish) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(dish.getCreationDate());
            audit.setText("Удалено блюдо " + dish.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.DISH);
            audit.setEntityId(dish.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }
}
