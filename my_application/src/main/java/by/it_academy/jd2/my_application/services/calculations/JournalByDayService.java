package by.it_academy.jd2.my_application.services.calculations;

import by.it_academy.jd2.my_application.dto.JournalByDateDto;
import by.it_academy.jd2.my_application.models.Component;
import by.it_academy.jd2.my_application.models.Dish;
import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.models.Product;
import by.it_academy.jd2.my_application.services.calculations.api.IJournalByDayService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalByDayService implements IJournalByDayService {

    @Override
    public JournalByDateDto getJournalByDay(List<Journal> journals) {
        JournalByDateDto journalByDay = new JournalByDateDto();

        List<Journal> journalList = new ArrayList<>();
        double sumOfCalories = 0;

        for (Journal journal : journals) {
            journalList.add(journal);
            double measure = journal.getMeasure();
            Dish dish = journal.getDish();
            Product product = journal.getProduct();

            if (product != null) {
                double productCalories = product.getCalories();
                double productMeasure = product.getMeasure();
                double calories = productCalories * measure / productMeasure;
                sumOfCalories += calories;
            }

            if (dish != null) {
                double dishMeasure = 0;
                double dishCalories = 0;
                List<Component> components = dish.getComponents();
                for (Component component : components) {
                    Product componentProduct = component.getProduct();
                    double componentProductCalories = componentProduct.getCalories();
                    double componentProductMeasure = componentProduct.getMeasure();
                    Double componentMeasure = component.getMeasure();
                    dishMeasure += componentMeasure;
                    dishCalories += componentProductCalories * componentMeasure / componentProductMeasure;
                    double calories = dishCalories * measure / dishMeasure;
                    sumOfCalories += calories;
                }
            }
        }

        journalByDay.setJournals(journalList);
        journalByDay.setSumOfCalories(sumOfCalories);

        return journalByDay;
    }
}
