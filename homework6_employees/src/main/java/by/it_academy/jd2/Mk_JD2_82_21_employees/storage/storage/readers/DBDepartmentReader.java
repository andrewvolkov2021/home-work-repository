package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBNewInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBDepartmentReader {
    private static final DBDepartmentReader instance = new DBDepartmentReader();

    private DBDepartmentReader(){
    }

    public List<Department> getListOfDepartments(){
        List<Department> listOfDepartments = new ArrayList<>();
        try (Connection con = DBNewInitializer.getConnection();
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

    public Department getDepartment(long id){
        Department department = null;

        try (Connection con = DBNewInitializer.getConnection();
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

    public Map<Long, Department> getMapOfDepartments(){
        Map<Long, Department> mapOfDepartments = new HashMap<>();
        try (Connection con = DBNewInitializer.getConnection();
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

    public static DBDepartmentReader getInstance(){
        return instance;
    }
}
