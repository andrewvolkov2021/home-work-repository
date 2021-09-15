package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBNewInitializer {
    private static final ComboPooledDataSource cpds;

    static {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException ex) {
            throw new RuntimeException("Ошибка загрузки драйвера", ex);
        }
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/employees");
        cpds.setUser("postgres");
        cpds.setPassword("mir2020mir");
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

}
