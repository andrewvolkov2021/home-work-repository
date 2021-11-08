package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IAuditDao;
import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.servicies.api.IAuditService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService implements IAuditService {

    private final IAuditDao auditDao;

    public AuditService(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public List<Audit> getListOfAudits(long page, long size, LocalDateTime begin, LocalDateTime end) {
        return auditDao.findAll();
    }

    @Override
    public void createAudit(Audit audit) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        audit.setCreationDate(creationDate);
        auditDao.save(audit);
    }

    @Override
    public Audit getAudit(long id) throws IllegalArgumentException {
        return auditDao.findById(id).orElseThrow();
    }

    @Override
    public void deleteAudit(long id, LocalDateTime dt_update) throws IllegalArgumentException {
        auditDao.deleteById(id);
    }
}
