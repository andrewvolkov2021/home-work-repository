package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.User;
import by.it_academy.jd2.my_application.models.api.EActivity;
import by.it_academy.jd2.my_application.models.api.ESex;
import by.it_academy.jd2.my_application.models.api.ETarget;

import java.time.LocalDateTime;

public class ProfileDto {

    private User user;
    private double height;
    private double weight;
    private Long birthdayDate;
    private ESex sex;
    private EActivity activity;
    private ETarget target;
    private double targetWeight;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Long getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Long birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public ESex getSex() {
        return sex;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public EActivity getActivity() {
        return activity;
    }

    public void setActivity(EActivity activity) {
        this.activity = activity;
    }

    public ETarget getTarget() {
        return target;
    }

    public void setTarget(ETarget target) {
        this.target = target;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }
}
