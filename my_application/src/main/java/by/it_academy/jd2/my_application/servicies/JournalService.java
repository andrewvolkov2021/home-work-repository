package by.it_academy.jd2.my_application.servicies;

import by.it_academy.jd2.my_application.dao.api.IJournalDao;
import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.servicies.api.IJournalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalService implements IJournalService {

    private final IJournalDao journalDao;

    public JournalService(IJournalDao journalDao) {
        this.journalDao = journalDao;
    }

    @Override
    public void createJournal(Journal journal) throws IllegalArgumentException {
        LocalDateTime creationDate = LocalDateTime.now();
        journal.setCreationDate(creationDate);
        journal.setUpdateDate(creationDate);
        journalDao.save(journal);
    }

    @Override
    public List<Journal> getListOfJournals(long page, long size) {
        return journalDao.findAll();
    }

    @Override
    public List<Journal> getListOfJournalsFPerDay(LocalDateTime day) {
        return journalDao.findAll();
    }

    @Override
    public Journal getJournal(long id) throws IllegalArgumentException {
        return journalDao.findById(id).orElseThrow();
    }

    @Override
    public void updateJournal(Journal journal, long id, LocalDateTime dt_update) throws IllegalArgumentException {
        Journal updatedJournal = getJournal(id);
        updatedJournal.setEating(journal.getEating());
        updatedJournal.setProfile(journal.getProfile());
        updatedJournal.setProduct(journal.getProduct());
        updatedJournal.setDish(journal.getDish());
        updatedJournal.setMeasure(journal.getMeasure());
        updatedJournal.setCreationDate(journal.getCreationDate());

        LocalDateTime updateDate = LocalDateTime.now();
        updatedJournal.setUpdateDate(updateDate);

        journalDao.save(updatedJournal);
    }

    @Override
    public void deleteJournal(long id, LocalDateTime dt_update) throws IllegalArgumentException{
        journalDao.deleteById(id);
    }
}
