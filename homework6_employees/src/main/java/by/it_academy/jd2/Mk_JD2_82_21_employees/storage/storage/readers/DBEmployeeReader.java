package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.SearchEmployee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.DBNewInitializer;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.initialiazers.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBEmployeeReader {
    private static final DBEmployeeReader instance = new DBEmployeeReader();

    private DBEmployeeReader(){
    }

    public Employee getEmployee(long id, Map<Long, Department> mapOfDepartments,
                                Map<Long, Position> mapOfPositions){
        Employee employee = null;

        try (Connection con = DBNewInitializer.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT id, name, salary, department, position " +
                     "FROM application.employees WHERE id = ? ");
        ){
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

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

    public List<Employee> getFullSortedListOfEmployees(SearchEmployee searchEmployee){
        String name = searchEmployee.getName();
        double minSalary = searchEmployee.getMinSalary();
        double maxSalary = searchEmployee.getMaxSalary();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> rootAuto = criteriaQuery.from(Employee.class);

        if (name.equals("")){
            criteriaQuery.where(criteriaBuilder.between(rootAuto.get("salary"), minSalary, maxSalary));
        } else {
            criteriaQuery.where(criteriaBuilder.and(
                    criteriaBuilder.equal(rootAuto.get("name"), name),
                    criteriaBuilder.between(rootAuto.get("salary"), minSalary, maxSalary)
            ));
        }
        List<Employee> list = session.createQuery(criteriaQuery).getResultList();
        session.close();
        //HibernateUtil.shutdown();
        return list;
    }


    public List<Employee> getSortedListOfEmployee(long offset, long limit, List<Employee> fullList){

        int sizeResultList = fullList.size();
        int sum = 0;
        if (sizeResultList < (offset + limit)){
            sum = sizeResultList;
        } else {
            sum = (int) (offset + limit);
        }
        return fullList.subList((int) offset, sum);
    }

    public static DBEmployeeReader getInstance(){
        return instance;
    }
}
