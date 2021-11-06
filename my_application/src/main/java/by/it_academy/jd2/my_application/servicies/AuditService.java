package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IAuditDao;
import by.it_academy.jd2.my_application.models.Audit;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService implements IEntityService<Audit> {

    private final IAuditDao auditDao;

    public AuditService(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public void createEntity(Audit audit) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        audit.setCreationDate(creationDate);
        audit.setUpdateDate(creationDate);
        auditDao.save(audit);
    }

    @Override
    public List<Audit> getAllEntities() {
        return auditDao.findAll();
    }

    @Override
    public Audit getEntity(long id) throws IllegalArgumentException  {
        return auditDao.findById(id).orElseThrow();
    }

    @Override
    public void updateEntity(Audit audit, long id) throws IllegalArgumentException {
        Audit updatedAudit = getEntity(id);
        updatedAudit.setUser(audit.getUser());
        updatedAudit.setText(audit.getText());
        updatedAudit.setTypeOfEntity(audit.getTypeOfEntity());
        updatedAudit.setIdOfEntity(audit.getIdOfEntity());
        updatedAudit.setCreator(audit.getCreator());
        updatedAudit.setCreationDate(audit.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedAudit.setUpdateDate(updateDate);

        auditDao.save(updatedAudit);
    }

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException {
        auditDao.deleteById(id);
    }
}
