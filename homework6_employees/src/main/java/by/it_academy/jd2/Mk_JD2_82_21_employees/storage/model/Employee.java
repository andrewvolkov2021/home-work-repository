package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model;

import java.math.BigDecimal;

public class Employee {
    private long id;
    private String name;
    private double salary;
    private Department department;
    private Position position;

    public Employee() {
    }

    public Employee(long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(long id, String name, double salary, Department department, Position position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public Position getPosition() {
        return position;
    }
}
