package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.DBNewInitializer;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDepartmentReader {
    private static DBDepartmentReader instance = new DBDepartmentReader();

    private DBDepartmentReader(){
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Ошибка загрузки драйвера", ex);
        }
    }

    public List<Department> getListOfDepartments(){
        List<Department> listOfDepartments = new ArrayList<>();
        try (Connection con = DBNewInitializer.getPoolDataSource().getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name_department, parental_department " +
                    "FROM application.departments")){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    long parentalId = resultSet.getLong(3);
                    Department parentalDepartment;
                    if ( parentalId == 0) {
                        parentalDepartment = new Department("\"Родительский отдел не указан\"");
                    } else {
                        parentalDepartment = getDepartment(parentalId);
                    }

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

        String sql = "SELECT id, name_department, parental_department FROM application.departments " +
                "WHERE id = " + id;
        try (Connection con = DBNewInitializer.getPoolDataSource().getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){

                while (resultSet.next()){
                    String name = resultSet.getString(2);
                    long parentalId = resultSet.getLong(3);
                    Department parentalDepartment;

                    if ( parentalId == 0) {
                        parentalDepartment = new Department("\"Родительский отдел не указан\"");
                    } else {
                        parentalDepartment = getDepartment(parentalId);
                    }
                    department = new Department(id, name, parentalDepartment);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return department;
    }

    public static DBDepartmentReader getInstance(){
        return instance;
    }
}
