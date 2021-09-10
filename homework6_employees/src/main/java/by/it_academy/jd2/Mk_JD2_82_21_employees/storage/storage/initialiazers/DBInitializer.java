package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers;

import java.sql.*;

public class DBInitializer {
    private static DBInitializer instance = new DBInitializer();

    private DBInitializer(){
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

    public static DBInitializer getInstance() {
        return instance;
    }
}
