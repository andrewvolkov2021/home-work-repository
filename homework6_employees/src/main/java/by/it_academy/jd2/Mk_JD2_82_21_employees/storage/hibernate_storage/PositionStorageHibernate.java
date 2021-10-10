package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.hibernate_storage;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IPositionStorage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionStorageHibernate implements IPositionStorage {

    private final SessionFactory sessionFactory;

    public PositionStorageHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Map<Long, Position> getMapOfPositions() {
        Map<Long, Position> mapOfPosition = new HashMap<>();
        List<Position> listOfPositions = getListOfPositions();
        listOfPositions.forEach(x -> mapOfPosition.put(x.getId(), x));
        return mapOfPosition;
    }

    @Override
    public Position getPosition(long id) {
        Session sessionOne = sessionFactory.openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);
        criteriaQuery.select(itemRoot);
        criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("id"), id));
        Position position = sessionOne.createQuery(criteriaQuery).getSingleResult();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return position;
    }

    @Override
    public List<Position> getListOfPositions() {
        Session sessionOne = sessionFactory.openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);
        criteriaQuery.select(itemRoot);
        List<Position> positionList = sessionOne.createQuery(criteriaQuery).getResultList();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return positionList;
    }

    @Override
    public void autoAddingPositions(List<Position> listOfPosition) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listOfPosition.forEach(x -> session.save(x));
        session.getTransaction().commit();
        session.close();
    }
}
