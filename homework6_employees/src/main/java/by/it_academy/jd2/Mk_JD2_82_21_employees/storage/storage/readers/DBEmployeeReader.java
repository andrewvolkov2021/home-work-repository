package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBNewInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBEmployeeReader {
    private static DBEmployeeReader instance = new DBEmployeeReader();

    private DBEmployeeReader(){
    }

    public List<Employee> getListOfEmployee(Map<Long, Department> mapOfDepartments,
                                            Map<Long, Position> mapOfPositions){
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DBNewInitializer.getConnection();
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

                    Department department = mapOfDepartments.get(idDepartment);
                    Position position = mapOfPositions.get(idPosition);

                    Employee employee = new Employee(id, name, salary, department, position);
                    listOfEmployees.add(employee);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfEmployees;
    }

    public Employee getEmployee(long id, Map<Long, Department> mapOfDepartments,
                                Map<Long, Position> mapOfPositions){
        Employee employee = null;

        String sql = "SELECT id, name, salary, department, position FROM application.employees WHERE id = " + id;
        try (Connection con = DBNewInitializer.getConnection();
             Statement statement = con.createStatement();
        ){
            try(ResultSet resultSet = statement.executeQuery(sql)){

                while (resultSet.next()){
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    long idDepartment = resultSet.getLong(4);
                    long idPosition = resultSet.getLong(5);

                    Department department = mapOfDepartments.get(idDepartment);
                    Position position = mapOfPositions.get(idPosition);

                    employee = new Employee(id, name, salary, department, position);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return employee;
    }

    public long getCountOfRecords() {
        long count = 0;
        try (Connection con = DBNewInitializer.getConnection();
             Statement statement = con.createStatement()
        ) {
            try (ResultSet resultSet = statement.executeQuery("SELECT count(id) FROM application.employees")) {
                while (resultSet.next()){
                    count = resultSet.getLong(1);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return count;
    }

    public List<Employee> getSelectListOfEmployee(long limit, long offset, Map<Long, Department> mapOfDepartments,
                                            Map<Long, Position> mapOfPositions){
        List<Employee> listOfEmployees = new ArrayList<>();
        try (Connection con = DBNewInitializer.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT id, name, salary, department, position " +
                     "FROM application.employees ORDER BY id ASC LIMIT ? OFFSET ?")
        ){
            ps.setLong(1, limit);
            ps.setLong(2, offset);

            try(ResultSet resultSet = ps.executeQuery()){

                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    double salary = resultSet.getDouble(3);
                    long idDepartment = resultSet.getLong(4);
                    long idPosition = resultSet.getLong(5);

                    Department department = mapOfDepartments.get(idDepartment);
                    Position position = mapOfPositions.get(idPosition);

                    Employee employee = new Employee(id, name, salary, department, position);
                    listOfEmployees.add(employee);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return listOfEmployees;
    }


    public static DBEmployeeReader getInstance(){
        return instance;
    }
}
