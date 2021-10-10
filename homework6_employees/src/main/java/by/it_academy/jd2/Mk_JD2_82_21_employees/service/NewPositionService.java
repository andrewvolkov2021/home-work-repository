package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.service.api.IPositionService;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.api.IPositionStorage;

import java.util.List;
import java.util.Map;

public class NewPositionService implements IPositionService {

    private final IPositionStorage positionStorage;
    
    public NewPositionService(IPositionStorage positionStorage){
        this.positionStorage = positionStorage;
    }
    
    @Override
    public Map<Long, Position> getMapOfPositions() {
        return positionStorage.getMapOfPositions();
    }

    @Override
    public List<Position> getListOfPositions() {
        return positionStorage.getListOfPositions();
    }

    @Override
    public Position getPosition(long id) {
        return positionStorage.getPosition(id);
    }

    @Override
    public void autoAddingPositions(List<Position> listOfPositions) {
        positionStorage.autoAddingPositions(listOfPositions);
    }
}
