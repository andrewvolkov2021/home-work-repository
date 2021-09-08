package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Position {
    private long id;
    private String name;

    public Position(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
