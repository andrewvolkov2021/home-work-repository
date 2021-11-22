package by.it_academy.jd2.my_application.dto;

public class ProductDto {

    private String name;
    private String brand;
    private double calories;
    private double protein;
    private double fats;
    private double carbonates;
    private double measure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbonates() {
        return carbonates;
    }

    public void setCarbonates(double carbonates) {
        this.carbonates = carbonates;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }
}
