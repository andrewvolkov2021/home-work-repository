package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    private static DBReader instance = new DBReader();

    private DBReader(){
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Ошибка загрузки драйвера", ex);
        }
    }

    public List<String> getListOfDepartments(){
        List<String> listOfDepartments = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/employees",
                "postgres", "mir2020mir");
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT name_department" +
                    " FROM application.departments ORDER BY id ASC")){

                while (resultSet.next()){
                    listOfDepartments.add(resultSet.getString(1));
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfDepartments;
    }

    public static DBReader getInstance(){
        return instance;
    }
}
