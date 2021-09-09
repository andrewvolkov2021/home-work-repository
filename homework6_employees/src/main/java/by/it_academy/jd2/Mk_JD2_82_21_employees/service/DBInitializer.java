package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.DBNewInitializer;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;

import java.sql.*;

public class DBInitializer {
    private static DBInitializer instance = new DBInitializer();

    private DBInitializer(){
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Ошибка загрузки драйвера", ex);
        }
    }

    public long addEmployee(String name, double salary) {
        long id;
        try (Connection con = DBNewInitializer.getPoolDataSource().getConnection()
             ) {

            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employees(\n" +
                    "name, salary)\n" +
                    "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, salary);

                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

                    generatedKeys.next();
                      id = generatedKeys.getLong(1);
                }
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return id;
    }

    public Employee getEmployee(long idEmployee){
        Employee employee;
        String sgl = "SELECT id, name, salary FROM application.employees WHERE id =" + idEmployee;
        try (Connection connection = DBNewInitializer.getPoolDataSource().getConnection();
             Statement statement = connection.createStatement();
        ) {

            try(ResultSet resultSet = statement.executeQuery(sgl)){
                resultSet.next();

                long id = idEmployee;
                String name = resultSet.getString(2);
                Double salary = resultSet.getDouble(3);
                employee = new Employee(id, name, salary);
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employee;
    }

    public static DBInitializer getInstance() {
        return instance;
    }
}
