package by.it_academy.jd2.Mk_JD2_82_21_employees.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_employees.model.Position;

import java.util.List;
import java.util.Map;

public interface IPositionService {
    Map<Long, Position> getMapOfPositions();

    Position getPosition(long id);

    List<Position> getListOfPositions();

    void autoAddingPositions(List<Position> listOfPositions);
}
