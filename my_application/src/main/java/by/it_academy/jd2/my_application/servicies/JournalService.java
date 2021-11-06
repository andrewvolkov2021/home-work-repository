package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IJournalDao;
import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.servicies.api.IEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalService implements IEntityService<Journal> {

    private final IJournalDao journalDao;

    public JournalService(IJournalDao journalDao) {
        this.journalDao = journalDao;
    }

    @Override
    public void createEntity(Journal journal) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        journal.setCreationDate(creationDate);
        journal.setUpdateDate(creationDate);
        journalDao.save(journal);
    }

    @Override
    public List<Journal> getAllEntities() {
        return journalDao.findAll();
    }

    @Override
    public Journal getEntity(long id) throws IllegalArgumentException {
        return journalDao.findById(id).orElseThrow();
    }

    @Override
    public void updateEntity(Journal journal, long id) throws IllegalArgumentException {
        Journal updatedJournal = getEntity(id);
        updatedJournal.setTypeOfEating(journal.getTypeOfEating());
        updatedJournal.setUser(journal.getUser());
        updatedJournal.setProduct(journal.getProduct());
        updatedJournal.setDish(journal.getDish());
        updatedJournal.setMeasure(journal.getMeasure());
        updatedJournal.setCreator(journal.getCreator());
        updatedJournal.setCreationDate(journal.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedJournal.setUpdateDate(updateDate);

        journalDao.save(updatedJournal);
    }

    @Override
    public void deleteEntity(long id) throws IllegalArgumentException{
        journalDao.deleteById(id);
    }
}
