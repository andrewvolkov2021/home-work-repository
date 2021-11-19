package by.it_academy.jd2.my_application.services.dataBaseService.api;

import by.it_academy.jd2.my_application.models.Audit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IAuditGeneralService {

    void save(Audit audit);

    Page<Audit> getAll(Pageable pageable);

    Audit get(Long id);

    void delete (Long id, LocalDateTime dt_update);
}
