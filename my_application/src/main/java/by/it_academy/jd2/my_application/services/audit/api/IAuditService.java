package by.it_academy.jd2.my_application.services.audit.api;

import org.aspectj.lang.JoinPoint;

public interface IAuditService {

    void save(JoinPoint joinPoint);

    void update(JoinPoint joinPoint);

    void delete(JoinPoint joinPoint);
}
