package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Active;

import java.util.List;

public class ActiveByDateDto {
    private List<Active> actives;
    private double sumOfCalories;

    public List<Active> getActives() {
        return actives;
    }

    public void setActives(List<Active> actives) {
        this.actives = actives;
    }

    public double getSumOfCalories() {
        return sumOfCalories;
    }

    public void setSumOfCalories(double sumOfCalories) {
        this.sumOfCalories = sumOfCalories;
    }
}
