package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IDepartmentStorage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentStorageHibernate implements IDepartmentStorage {

    private final SessionFactory sessionFactory;

    public DepartmentStorageHibernate(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Map<Long, Department> getMapOfDepartments() {
        Map<Long, Department> mapOfDepartment = new HashMap<>();
        List<Department> listOfDepartments = getListOfDepartments();
        listOfDepartments.forEach(x -> mapOfDepartment.put(x.getId(), x));
        return mapOfDepartment;
    }

    @Override
    public Department getDepartment(long id) {
               Session sessionOne = sessionFactory.openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> itemRoot = criteriaQuery.from(Department.class);
        criteriaQuery.select(itemRoot);
        criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("id"), id));
        Department department = sessionOne.createQuery(criteriaQuery).getSingleResult();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return department;
    }

    @Override
    public List<Department> getListOfDepartments() {
        Session sessionOne = sessionFactory.openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> itemRoot = criteriaQuery.from(Department.class);
        criteriaQuery.select(itemRoot);
        List<Department> departmentList = sessionOne.createQuery(criteriaQuery).getResultList();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return departmentList;
    }

    @Override
    public void autoAddingDepartments(List<Department> listOfDepartments) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listOfDepartments.forEach(x -> session.save(x));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Long> getListOfDepartmentId() {
        Session sessionOne = sessionFactory.openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Department> itemRoot = criteriaQuery.from(Department.class);
        criteriaQuery.select(itemRoot.get("id"));
        List<Long> departmentListId = sessionOne.createQuery(criteriaQuery).getResultList();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return departmentListId;
    }

    @Override
    public void autoAddingParentalDepartment(Long[] array, Integer[] arrayOfParentalDepartment) {
        Session sessionOne = sessionFactory.openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();

        for (int i = 0; i < array.length; i++) {
            long departmentId = array[i];
            int a = arrayOfParentalDepartment[i];
            if (a != 0) {
                long parentalId = array[a - 1];

                CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
                Root<Department> itemRoot = criteriaQuery.from(Department.class);
                criteriaQuery.select(itemRoot);

                criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("id"), departmentId));
                Department department = sessionOne.createQuery(criteriaQuery).getSingleResult();

                criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("id"), parentalId));
                Department parentalDepartment = sessionOne.createQuery(criteriaQuery).getSingleResult();

                department.setParentalDepartment(parentalDepartment);
                sessionOne.update(department);
            }
        }
        sessionOne.getTransaction().commit();
        sessionOne.close();
    }
}
