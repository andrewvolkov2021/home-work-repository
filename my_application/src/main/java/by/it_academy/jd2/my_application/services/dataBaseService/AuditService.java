package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IAuditDao;
import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IAuditGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService implements IAuditGeneralService {

    private final IAuditDao auditDao;

    public AuditService(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public void save(Audit audit) {
        LocalDateTime creationDate = LocalDateTime.now();
        audit.setCreationDate(creationDate);
        auditDao.save(audit);
    }

    @Override
    public Page<Audit> getAll(Pageable pageable) {
        return auditDao.findAll(pageable);
    }

    @Override
    public Audit get(Long id) throws IllegalArgumentException {
        return auditDao.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id, LocalDateTime dt_update){
        auditDao.deleteById(id);
    }
}
