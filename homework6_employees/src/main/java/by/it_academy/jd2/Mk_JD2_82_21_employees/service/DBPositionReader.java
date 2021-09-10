package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.DBNewInitializer;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBPositionReader {
    private static DBPositionReader instance = new DBPositionReader();

    private DBPositionReader(){
    }

    public List<Position> getListOfPosition(){
        List<Position> listOfPositions = new ArrayList<>();
        try (Connection con = DBNewInitializer.getPoolDataSource().getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name_position " +
                    "FROM application.positions")){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);

                    Position position = new Position(id, name);
                    listOfPositions.add(position);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfPositions;
    }

    public Position getPosition(long id){
        Position position = null;

        String sql = "SELECT id, name_position FROM application.positions " +
                "WHERE id = " + id;
        try (Connection con = DBNewInitializer.getPoolDataSource().getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){

                while (resultSet.next()){
                    String name = resultSet.getString(2);
                    position = new Position(id, name);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return position;
    }

    public static DBPositionReader getInstance(){
        return instance;
    }
}
