package by.it_academy.jd2.Mk_JD2_82_21_employees.storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;

public class DBNewInitializer {

    public static ComboPooledDataSource getPoolDataSource(){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException ex) {
            throw new RuntimeException("Ошибка драйвера загрузки", ex);
        }
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/employees");
        cpds.setUser("postgres");
        cpds.setPassword("mir2020mir");

        return cpds;
    }
}
