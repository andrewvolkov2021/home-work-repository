package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainRunnerService {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("storage.xml");

    }
}
