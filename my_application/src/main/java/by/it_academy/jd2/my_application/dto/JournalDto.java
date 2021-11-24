package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.models.Profile;
import by.it_academy.jd2.my_application.models.api.EEating;

public class JournalDto {

    private Dish dish;
    private Product product;
    private double measure;
    private EEating eating;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public EEating getEating() {
        return eating;
    }

    public void setEating(EEating eating) {
        this.eating = eating;
    }
}
