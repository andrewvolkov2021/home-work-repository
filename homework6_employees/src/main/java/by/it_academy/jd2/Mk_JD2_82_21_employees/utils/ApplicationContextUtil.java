package by.it_academy.jd2.Mk_JD2_82_21_employees.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {
    private static ClassPathXmlApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("service.xml");
    }

    public static ApplicationContext getContext(){
        return context;
    }

    public void close(){
        context.close();
    }
}
