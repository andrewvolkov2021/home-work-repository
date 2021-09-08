package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model;

public class Department {
    private long id;
    private String name;
    private String parentalName;

    public Department(long id, String name, String parentalName) {
        this.id = id;
        this.name = name;
        this.parentalName = parentalName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentalName() {
        return parentalName;
    }
}
