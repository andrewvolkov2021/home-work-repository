package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBNewInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBPositionReader {
    private static final DBPositionReader instance = new DBPositionReader();

    private DBPositionReader(){
    }

    public List<Position> getListOfPosition(){
        List<Position> listOfPositions = new ArrayList<>();
        try (Connection con = DBNewInitializer.getConnection();
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

        try (Connection con = DBNewInitializer.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT id, name_position " +
                     "FROM application.positions WHERE id = ?");
        ){
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

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

    public Map<Long, Position> getMapOfPositions(){
        Map<Long, Position> mapOfPositions = new HashMap<>();
        try (Connection con = DBNewInitializer.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name_position " +
                    "FROM application.positions")){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);

                    Position position = new Position(id, name);
                    mapOfPositions.put(id, position);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return mapOfPositions;
    }

    public static DBPositionReader getInstance(){
        return instance;
    }
}
