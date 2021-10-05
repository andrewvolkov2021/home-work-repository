package by.it_academy.jd2.Mk_JD2_82_21_employees.model;

public class EmployeeSearchFilter extends PageableFilter{
    private String name;
    private String  minSalary;
    private String maxSalary;

    public EmployeeSearchFilter(String page, String size, String name, String minSalary, String maxSalary) {
        super(page, size);
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }
}

