package by.it_academy.jd2.my_application.dto;

import by.it_academy.jd2.my_application.models.Weighting;
import org.springframework.data.domain.Page;

public class WeightingByDateDto {

    Page<Weighting> weightings;

    public Page<Weighting> getWeightings() {
        return weightings;
    }

    public void setWeightings(Page<Weighting> weightings) {
        this.weightings = weightings;
    }
}
