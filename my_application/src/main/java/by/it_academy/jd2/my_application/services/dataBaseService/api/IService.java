package by.it_academy.jd2.my_application.services.dataBaseService.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IService<T, ID> {

    void save(T item);

    Page<T> getAll(Pageable pageable);

    T get(ID id);

    void update(T item, ID id, LocalDateTime dt_update);

    void delete (ID id, LocalDateTime dt_update);
}
