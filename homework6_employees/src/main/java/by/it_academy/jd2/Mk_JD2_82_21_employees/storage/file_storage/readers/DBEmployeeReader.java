package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.file_storage.readers;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.model.SearchEmployee;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.initialiazers.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.List;

public class DBEmployeeReader {
    private static final DBEmployeeReader instance = new DBEmployeeReader();

    private DBEmployeeReader(){
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
