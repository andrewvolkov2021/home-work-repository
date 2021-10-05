package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.*;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IEmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.initialiazers.DBNewInitializer;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.initialiazers.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeStorage implements IEmployeeStorage {

    private static final EmployeeStorage instance = new EmployeeStorage();

    private EmployeeStorage() {
    }

    @Override
    public long addEmployee(Employee employee) {
        long id;
        String name = employee.getName();
        double salary = employee.getSalary();

        try (Connection con = DBNewInitializer.getConnection()
        ) {

            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employees(\n" +
                    "name, salary)\n" +
                    "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, salary);


                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

                    generatedKeys.next();
                    id = generatedKeys.getLong(1);
                }
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Ошибка при работе с базой данных", ex);
        }
        return id;
    }

    @Override
    public Employee getEmployee(long id, Map<Long, Department> mapOfDepartments,Map<Long, Position> mapOfPositions) {
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

    @Override
    public List<Employee> getListOfEmployees(long limit, long offset, Map<Long, Department> mapOfDepartments,
                                             Map<Long, Position> mapOfPositions) {
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

    @Override
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

    @Override
    public void autoAddingOfEmployees(List<Employee> listOfEmployee) {
        listOfEmployee.forEach(x -> {
            try (Connection con = DBNewInitializer.getConnection();
                 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employees(\n" +
                         "name, salary, department, position)\n" + "VALUES (?, ?, ?, ?);")
            ){

                preparedStatement.setString(1, x.getName());
                preparedStatement.setDouble(2, x.getSalary());
                preparedStatement.setLong(3, x.getDepartment().getId());
                preparedStatement.setLong(4, x.getPosition().getId());

                preparedStatement.executeUpdate();

            }  catch (SQLException ex) {
                throw new IllegalStateException("Ошибка при работе с базой данных", ex);
            }
        });
    }

    @Override
    public List<Employee> getFullSortedList(EmployeeSearchFilter filter){
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = getCriteriaQuery(filter, criteriaBuilder);

        Query<Employee> query = sessionOne.createQuery(criteriaQuery);

        sessionOne.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public List<Employee> getSortedList(EmployeeSearchFilter filter) {

        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = getCriteriaQuery(filter, criteriaBuilder);

        Query<Employee> query = sessionOne.createQuery(criteriaQuery);

        int size = Integer.parseInt(filter.getSize());
        if (size == 0) {
            size = 20;
        }
        query.setFirstResult((Integer.parseInt(filter.getPage()) -1) * size);
        query.setMaxResults(size);

        List<Employee> resultList = query.getResultList();

        sessionOne.getTransaction().commit();
        return resultList;
    }

    private CriteriaQuery<Employee> getCriteriaQuery(EmployeeSearchFilter filter, CriteriaBuilder criteriaBuilder){
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();
        if(filter.getName() != null && !filter.getName().equals("")){
            predicates.add(criteriaBuilder.equal(itemRoot.get("name"), filter.getName()));
        }

        if (!filter.getMinSalary().equals("") && filter.getMinSalary() != null){
            predicates.add(criteriaBuilder.ge(itemRoot.get("salary"), Double.parseDouble(filter.getMinSalary())));
        }

        if (!filter.getMaxSalary().equals("") && filter.getMaxSalary() != null){
            predicates.add(criteriaBuilder.le(itemRoot.get("salary"), Double.parseDouble(filter.getMaxSalary())));
        }

        if (filter.getMinSalary().equals("") || filter.getMinSalary() == null ||
                filter.getMaxSalary().equals("") || filter.getMaxSalary() == null) {
            throw new IllegalArgumentException("Передано неверное сообщение");
        }

        if(!filter.getMinSalary().equals("") && !filter.getMaxSalary().equals("") &&
                Double.parseDouble(filter.getMinSalary()) > Double.parseDouble(filter.getMaxSalary())) {
            throw new IllegalArgumentException("Минимальное значение зарплаты сотрудника должно быть " +
                    "меньше либо равно максимальному значению");
        }

        Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);

        Expression<Boolean> restriction = criteriaBuilder.and(predicatesArray);
        return criteriaQuery.where(restriction);
    }

    public static EmployeeStorage getInstance(){
        return instance;
    }
}
