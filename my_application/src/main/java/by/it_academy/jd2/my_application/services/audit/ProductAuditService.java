package by.it_academy.jd2.my_application.services.audit;

import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.models.Product;
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
public class ProductAuditService {

    private final IAuditGeneralService auditGeneralService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProductAuditService(IAuditGeneralService auditGeneralService, UserHolder userHolder, IUserService userService) {
        this.auditGeneralService = auditGeneralService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.my_application.services.dataBaseService.ProductService.save(..))", returning = "result")
    public void methodSaveProduct(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Product product = (Product) result;

            Audit audit = new Audit();
            audit.setCreationDate(product.getCreationDate());
            audit.setText("Создан Product " + product.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PRODUCT);
            audit.setEntityId(product.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.ProductService.update(..))")
    public void methodUpdateProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Product arg = (Product) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(arg.getCreationDate());
            audit.setText("Изменен Product " + arg.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PRODUCT);
            audit.setEntityId(arg.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.ProductService.delete(..))")
    public void methodDeleteProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];
            Audit audit = new Audit();
            audit.setCreationDate(LocalDateTime.now().withNano(0));
            audit.setText("Удален Product " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.PRODUCT);
            audit.setEntityId(id);
           auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
