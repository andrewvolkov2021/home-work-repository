package by.it_academy.jd2.my_application.services.calculations.api;

import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.models.Active;

import java.util.List;

public interface IActiveByDateService {
    ActiveByDateDto getActiveByDate(List<Active> actives);
}
