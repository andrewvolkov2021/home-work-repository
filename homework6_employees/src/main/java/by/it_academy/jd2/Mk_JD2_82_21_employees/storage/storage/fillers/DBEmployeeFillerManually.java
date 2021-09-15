package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.fillers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBNewInitializer;

import java.sql.*;

public class DBEmployeeFillerManually {
    private static DBEmployeeFillerManually instance = new DBEmployeeFillerManually();

    private DBEmployeeFillerManually(){
    }

    public long addEmployee(String name, double salary) {
        long id;
        try (Connection con = DBNewInitializer.getConnection()
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

    public static DBEmployeeFillerManually getInstance() {
        return instance;
    }
}
