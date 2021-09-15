package by.it_academy.jd2.Mk_JD2_82_21_employees.service;

import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21_employees.storage.storage.readers.DBPositionReader;

import java.util.List;
import java.util.Map;

public class PositionService {
    private static PositionService instance = new PositionService();

    private PositionService(){
    }

    public List<Position> getListOfPosition(){
        return DBPositionReader.getInstance().getListOfPosition();
    }

    public Position getPosition(long id){
        return DBPositionReader.getInstance().getPosition(id);
    }

    public Map<Long, Position> getMapOfPositions(){
        return DBPositionReader.getInstance().getMapOfPositions();
    }

    public static PositionService getInstance(){
        return instance;
    }
}
