package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.audit.api.IAuditService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IAuditGeneralService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

public class ProductAuditService implements IAuditService {

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProductAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.ProductService.save(..))")
    public void save(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(product.getCreationDate());
            audit.setText("Создан продукт " + product.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PRODUCT);
            audit.setEntityId(product.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.ProductService.update(..))")
    public void update(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(product.getCreationDate());
            audit.setText("Изменен продукт " + product.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PRODUCT);
            audit.setEntityId(product.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }

    @Override
    @After("execution(by.it_academy.jd2.my_application.services.dataBaseService.ProductService.delete(..))")
    public void delete(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(product.getCreationDate());
            audit.setText("Удален продукт " + product.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PRODUCT);
            audit.setEntityId(product.getId());
            auditGeneralService.save(audit);
        } catch (Throwable e) {
            throw new RuntimeException("Ошибка при создании события Audit");
        }
    }
}
