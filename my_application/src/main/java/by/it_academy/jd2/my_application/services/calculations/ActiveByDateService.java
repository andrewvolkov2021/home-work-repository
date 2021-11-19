package by.it_academy.jd2.my_application.services.calculations;

import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.services.calculations.api.IActiveByDateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveByDateService implements IActiveByDateService {

    public ActiveByDateService() {
    }

    @Override
    public ActiveByDateDto getActiveByDate(List<Active> actives) {
        ActiveByDateDto activeByDate = new ActiveByDateDto();
        double sumOfCalories = 0;
        for (Active active : actives) {
            sumOfCalories += active.getCalories();
        }

        activeByDate.setActives(actives);
        activeByDate.setSumOfCalories(sumOfCalories);
        return  activeByDate;
    }
}
