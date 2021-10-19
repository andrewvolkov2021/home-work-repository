package by.it_academy.jd2.Mk_JD2_82_21_employees.config;

import by.it_academy.jd2.Mk_JD2_82_21_employees.service.*;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.*;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IDepartmentStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IEmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IPositionStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IDepartmentService departmentService(IDepartmentStorage departmentStorageHibernate){
        return new NewDepartmentService(departmentStorageHibernate);
    }

    @Bean
    public IPositionService positionService(IPositionStorage positionStorageHibernate){
        return new NewPositionService(positionStorageHibernate);
    }

    @Bean
    public IEmployeeService employeeService(IEmployeeStorage employeeStorageHibernate,
                                            IDepartmentService departmentService, IPositionService positionService){
        return new NewEmployeeService(employeeStorageHibernate, departmentService, positionService);
    }

    @Bean
    public IPaginationService paginationService(){
        return new NewPaginationService();
    }

    @Bean
    public IAutoFillerService autoFillerService(IDepartmentService departmentService,
                                                IEmployeeService employeeService, IPositionService positionService){
        return new NewAutoFillerService(departmentService, employeeService, positionService);
    }
}
