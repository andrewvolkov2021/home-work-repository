package by.it_academy.jd2.my_application.services.calculations;

import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.services.calculations.api.IActiveByDateService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ActiveByDateService implements IActiveByDateService {

    @Override
    public ActiveByDateDto getActiveByDate(Page<Active> actives) {
        ActiveByDateDto activeByDateDto = new ActiveByDateDto();
        double sumOfCalories = 0;
        for (Active active : actives) {
            sumOfCalories += active.getCalories();
        }

        activeByDateDto.setActives(actives);
        activeByDateDto.setSumOfCalories(sumOfCalories);
        return  activeByDateDto;
    }
}
