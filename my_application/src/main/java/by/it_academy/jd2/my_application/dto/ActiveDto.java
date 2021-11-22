package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Profile;

public class ActiveDto {

    private String name;
    private double calories;
    private Profile profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
