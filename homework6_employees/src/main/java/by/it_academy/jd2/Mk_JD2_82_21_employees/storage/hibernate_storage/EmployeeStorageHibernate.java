package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.EmployeeSearchFilter;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IEmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.initialiazers.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeStorageHibernate implements IEmployeeStorage {

    private static final EmployeeStorageHibernate instance = new EmployeeStorageHibernate();

    private EmployeeStorageHibernate() {
    }

    @Override
    public long addEmployee(Employee employee) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        session.close();
        return employee.getId();
    }

    @Override
    public Employee getEmployee(long id, Map<Long, Department> mapOfDepartment, Map<Long, Position> mapOfPosition) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(itemRoot);
        criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("id"), id));
        Employee employee = sessionOne.createQuery(criteriaQuery).getSingleResult();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return employee;
    }

    @Override
    public List<Employee> getListOfEmployees(long limit, long offset, Map<Long, Department> mapOfDepartments, Map<Long, Position> mapOfPositions) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(itemRoot);

        Query<Employee> query = sessionOne.createQuery(criteriaQuery);

        query.setFirstResult((int) offset);
        query.setMaxResults((int) limit);

        List<Employee> employeesList = query.getResultList();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return employeesList;
    }

    @Override
    public long getCountOfRecords() {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.count(itemRoot));
        Query<Long> query = sessionOne.createQuery(criteriaQuery);
        Long count = query.getSingleResult();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return count;
    }

    @Override
    public void autoAddingOfEmployees(List<Employee> listOfEmployee) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listOfEmployee.forEach(x -> session.save(x));
        session.getTransaction().commit();
        session.close();
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

    @Override
    public List<Employee> getFullSortedList(EmployeeSearchFilter filter) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = getCriteriaQuery(filter, criteriaBuilder);

        Query<Employee> query = sessionOne.createQuery(criteriaQuery);

        sessionOne.getTransaction().commit();
        return query.getResultList();
    }

    private CriteriaQuery<Employee> getCriteriaQuery(EmployeeSearchFilter filter, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null && !filter.getName().equals("")) {
            predicates.add(criteriaBuilder.equal(itemRoot.get("name"), filter.getName()));
        }

        if (!filter.getMinSalary().equals("") && filter.getMinSalary() != null) {
            predicates.add(criteriaBuilder.ge(itemRoot.get("salary"), Double.parseDouble(filter.getMinSalary())));
        }

        if (!filter.getMaxSalary().equals("") && filter.getMaxSalary() != null) {
            predicates.add(criteriaBuilder.le(itemRoot.get("salary"), Double.parseDouble(filter.getMaxSalary())));
        }

        if (filter.getMinSalary().equals("") || filter.getMinSalary() == null ||
                filter.getMaxSalary().equals("") || filter.getMaxSalary() == null) {
            throw new IllegalArgumentException("Передано неверное сообщение");
        }

        if (!filter.getMinSalary().equals("") && !filter.getMaxSalary().equals("") &&
                Double.parseDouble(filter.getMinSalary()) > Double.parseDouble(filter.getMaxSalary())) {
            throw new IllegalArgumentException("Минимальное значение зарплаты сотрудника должно быть " +
                    "меньше либо равно максимальному значению");
        }

        Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);

        Expression<Boolean> restriction = criteriaBuilder.and(predicatesArray);
        return criteriaQuery.where(restriction);
    }

    public static EmployeeStorageHibernate getInstance(){
        return instance;
    }
}
