package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IDepartmentStorage;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentStorage implements IDepartmentStorage {

    private final ComboPooledDataSource connection;

    public DepartmentStorage(ComboPooledDataSource connection){
        this.connection = connection;
    }

    @Override
    public Department getDepartment(long id) {
        Department department = null;

        try (Connection con = connection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT id, name_department, parental_department" +
                     " FROM application.departments WHERE id = ? ");
        ){
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){
                    String name = resultSet.getString(2);
                    long parentalId = resultSet.getLong(3);

                    Department parentalDepartment = getDepartment(parentalId);
                    department = new Department(id, name, parentalDepartment);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return department;
    }

    @Override
    public Map<Long, Department> getMapOfDepartments() {
        Map<Long, Department> mapOfDepartments = new HashMap<>();
        try (Connection con = connection.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name_department, parental_department " +
                    "FROM application.departments")){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    long parentalId = resultSet.getLong(3);
                    Department parentalDepartment;
                    parentalDepartment = getDepartment(parentalId);
                    Department department = new Department(id, name, parentalDepartment);

                    mapOfDepartments.put(id, department);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return mapOfDepartments;
    }

    @Override
    public List<Department> getListOfDepartments() {
        List<Department> listOfDepartments = new ArrayList<>();
        try (Connection con = connection.getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name_department, parental_department " +
                    "FROM application.departments")){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    long parentalId = resultSet.getLong(3);
                    Department parentalDepartment;
                    parentalDepartment = getDepartment(parentalId);

                    Department department = new Department(id, name, parentalDepartment);
                    listOfDepartments.add(department);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfDepartments;
    }

    @Override
    public void autoAddingDepartments(List<Department> listOfDepartments) {
        try (Connection con = connection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.departments(\n" +
                     "name_department)\n" + "VALUES (?);")
        ){

            for (Department item : listOfDepartments) {
                preparedStatement.setString(1, item.getName());

                preparedStatement.executeUpdate();
            }

        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
    }

    @Override
    public List<Long> getListOfDepartmentId() {
        List<Long> listOfDepartmentId = new ArrayList<>();
        try (Connection con = connection.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id FROM application.departments")
        ){
            while (resultSet.next()){
                listOfDepartmentId.add(resultSet.getLong(1));
            }

        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfDepartmentId;
    }

    @Override
    public void autoAddingParentalDepartment(Long[] array, Integer[] arrayOfParentalDepartment) {
        try (Connection con = connection.getConnection();
        ){
            for (int i = 0; i < array.length; i++) {
                long departmentId = array[i];
                int a = arrayOfParentalDepartment[i];
                if (a != 0) {

                    long parentalId = array[a - 1];
                    try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE application.departments " +
                            "SET parental_department = ? WHERE id = ?");
                    ) {
                        preparedStatement.setLong(1, parentalId);
                        preparedStatement.setLong(2, departmentId);

                        preparedStatement.executeUpdate();
                    }
                }
            }
        }  catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
    }
}
