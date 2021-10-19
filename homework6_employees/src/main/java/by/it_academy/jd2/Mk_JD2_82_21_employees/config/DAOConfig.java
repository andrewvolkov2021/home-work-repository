package by.it_academy.jd2.Mk_JD2_82_21_employees.config;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage.DepartmentStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage.EmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage.PositionStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IDepartmentStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IEmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IPositionStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage.DepartmentStorageHibernate;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage.EmployeeStorageHibernate;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage.PositionStorageHibernate;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DAOConfig {

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.scanPackages("by.it_academy.jd2.Mk_JD2_82_21_employees.model");
        builder.setProperty("hibernate.show_sql", "true");
        builder.setProperty("hibernate.default_schema", "application");
        builder.setProperty("hibernate.hbm2ddl.auto", "update");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        return builder.buildSessionFactory();
    }

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Ошибка загрузки драйвера" ,e);
        }
        cpds.setJdbcUrl( "jdbc:postgresql://localhost:5432/employees" );
        cpds.setUser("postgres");
        cpds.setPassword("mir2020mir");
        return cpds;
    }

    @Bean
    public IDepartmentStorage departmentStorageHibernate(SessionFactory sessionFactory){
        return new DepartmentStorageHibernate(sessionFactory);
    }

    @Bean
    public IEmployeeStorage employeeStorageHibernate(SessionFactory sessionFactory){
        return new EmployeeStorageHibernate(sessionFactory);
    }

    @Bean
    public IPositionStorage positionStorageHibernate(SessionFactory sessionFactory){
        return new PositionStorageHibernate(sessionFactory);
    }

    @Bean
    public IDepartmentStorage departmentStorage(DataSource dataSource){
        return new DepartmentStorage((ComboPooledDataSource) dataSource);
    }

    @Bean
    public IEmployeeStorage employeeStorage(DataSource dataSource, SessionFactory sessionFactory){
        return new EmployeeStorage((ComboPooledDataSource) dataSource, sessionFactory);
    }

    @Bean
    public IPositionStorage positionStorage(DataSource dataSource) {
        return new PositionStorage((ComboPooledDataSource) dataSource);
    }
}
