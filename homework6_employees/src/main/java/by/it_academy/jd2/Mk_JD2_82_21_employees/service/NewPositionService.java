package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPositionService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.SQL_storage.PositionStorage;

import java.util.List;
import java.util.Map;

public class NewPositionService implements IPositionService {

    private static final NewPositionService instance = new NewPositionService();

    private NewPositionService() {
    }

    @Override
    public Map<Long, Position> getMapOfPositions() {
        return PositionStorage.getInstance().getMapOfPositions();
    }

    @Override
    public List<Position> getListOfPositions() {
        return PositionStorage.getInstance().getListOfPositions();
    }

    @Override
    public Position getPosition(long id) {
        return PositionStorage.getInstance().getPosition(id);
    }

    @Override
    public void autoAddingPositions(List<Position> listOfPositions) {
        PositionStorage.getInstance().autoAddingPositions(listOfPositions);
    }

    public static NewPositionService getInstance(){
        return instance;
    }
}
