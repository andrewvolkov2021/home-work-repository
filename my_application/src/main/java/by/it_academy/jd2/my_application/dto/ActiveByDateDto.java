package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Active;
import org.springframework.data.domain.Page;

public class ActiveByDateDto {

    private Page<Active> actives;
    private double sumOfCalories;

    public Page<Active> getActives() {
        return actives;
    }

    public void setActives(Page<Active> actives) {
        this.actives = actives;
    }

    public double getSumOfCalories() {
        return sumOfCalories;
    }

    public void setSumOfCalories(double sumOfCalories) {
        this.sumOfCalories = sumOfCalories;
    }
}
