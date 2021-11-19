package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Journal;

import java.util.List;

public class JournalByDateDto {
    private List<Journal> journals;
    private double sumOfCalories;

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public double getSumOfCalories() {
        return sumOfCalories;
    }

    public void setSumOfCalories(double sumOfCalories) {
        this.sumOfCalories = sumOfCalories;
    }
}
