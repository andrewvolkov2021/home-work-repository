package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Profile;

public class WeightingDto {

    private double weight;
    private Profile profile;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}

