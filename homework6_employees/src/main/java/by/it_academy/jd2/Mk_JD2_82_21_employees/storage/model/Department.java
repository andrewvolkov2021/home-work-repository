package by.it_academy.jd2.Mk_JD2_82_21_employees.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_department")
    private String name;

    @OneToOne
    @JoinColumn(name = "parental_department", referencedColumnName = "id")
    private Department parentalDepartment;

    public Department(String name) {
        this.name = name;
    }

    public Department(long id, String name, Department parentalDepartment) {
        this.id = id;
        this.name = name;
        this.parentalDepartment = parentalDepartment;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getParentalDepartment() {
        return parentalDepartment;
    }
}
