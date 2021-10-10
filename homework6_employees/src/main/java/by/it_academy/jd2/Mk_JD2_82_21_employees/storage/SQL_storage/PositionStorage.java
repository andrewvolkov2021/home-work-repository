package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IPositionStorage;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionStorage implements IPositionStorage {

    private final ComboPooledDataSource connection;

    public PositionStorage(ComboPooledDataSource connection){
        this.connection = connection;
    }

    @Override
    public Position getPosition(long id) {
        Position position = null;

        try (Connection con = connection.getConnection();
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

    @Override
    public Map<Long, Position> getMapOfPositions() {
        Map<Long, Position> mapOfPositions = new HashMap<>();
        try (Connection con = connection.getConnection();
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

    @Override
    public List<Position> getListOfPositions() {
        List<Position> listOfPositions = new ArrayList<>();
        try (Connection con = connection.getConnection();
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

    @Override
    public void autoAddingPositions(List<Position> listOfPosition) {
        try (Connection con = connection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.positions(\n" +
                     "name_position)\n" + "VALUES (?);")
        ){

            for (Position item : listOfPosition) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.executeUpdate();
            }
        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
    }
}
