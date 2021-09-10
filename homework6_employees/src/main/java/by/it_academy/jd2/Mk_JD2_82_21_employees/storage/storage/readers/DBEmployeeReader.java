package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBNewInitializer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBEmployeeReader {
    private static DBEmployeeReader instance = new DBEmployeeReader();

    private DBEmployeeReader(){
    }

    public List<Employee> getListOfEmployee(){
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DBNewInitializer.getPoolDataSource().getConnection();
             Statement statement = con.createStatement()
        ){
            try(ResultSet resultSet = statement.executeQuery("SELECT id, name, salary, department, position " +
                    "FROM application.employees")){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    long idDepartment = resultSet.getLong(4);
                    long idPosition = resultSet.getLong(5);

                    Department department = DBDepartmentReader.getInstance().getDepartment(idDepartment);
                    Position position = DBPositionReader.getInstance().getPosition(idPosition);

                    Employee employee = new Employee(id, name, salary, department, position);
                    listOfEmployees.add(employee);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfEmployees;
    }

    public Employee getEmployee(long id){
        Employee employee = null;

        String sql = "SELECT id, name, salary, department, position FROM application.employees WHERE id = " + id;
        try (Connection con = DBNewInitializer.getPoolDataSource().getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){

                while (resultSet.next()){
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    long idDepartment = resultSet.getLong(4);
                    long idPosition = resultSet.getLong(5);

                    Department department = DBDepartmentReader.getInstance().getDepartment(idDepartment);
                    Position position = DBPositionReader.getInstance().getPosition(idPosition);

                    employee = new Employee(id, name, salary, department, position);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employee;
    }

    public static DBEmployeeReader getInstance(){
        return instance;
    }
}
