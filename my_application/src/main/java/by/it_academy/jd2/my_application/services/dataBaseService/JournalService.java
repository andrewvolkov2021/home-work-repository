package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IJournalDao;
import by.it_academy.jd2.my_application.dto.JournalByDateDto;
import by.it_academy.jd2.my_application.dto.JournalDto;
import by.it_academy.jd2.my_application.models.Journal;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IJournalService;
import by.it_academy.jd2.my_application.services.calculations.api.IJournalByDayService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalService implements IJournalService {

    private final IJournalDao journalDao;
    private final IJournalByDayService journalByDayService;

    public JournalService(IJournalDao journalDao, IJournalByDayService journalByDayService) {
        this.journalDao = journalDao;
        this.journalByDayService = journalByDayService;
    }

    @Override
    public void save(JournalDto journalDto) {
        Journal journal = new Journal();
        journal.setDish(journalDto.getDish());
        journal.setProduct(journalDto.getProduct());
        journal.setEating(journalDto.getEating());
        journal.setProfile(journalDto.getProfile());
        journal.setMeasure(journalDto.getMeasure());

        LocalDateTime creationDate = LocalDateTime.now();
        journal.setCreationDate(creationDate);
        journal.setUpdateDate(creationDate);
        journalDao.save(journal);
    }

    @Override
    public Page<Journal> getAll(Pageable pageable) {
        return journalDao.findAll(pageable);
    }

    @Override
    public Page<Journal> getAllByProfileId(Pageable pageable, Long profileId) {
        return journalDao.findByProfileId(profileId, pageable);
    }

    @Override
    public JournalByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end, Long id) {
        List<Journal> journals = journalDao.findAllByCreationDateBetweenAndProfileId(start, end, id);
        return journalByDayService.getJournalByDay(journals);
    }

    @Override
    public List<Journal> findAllByProfile(Long id) {
        return journalDao.findAllByProfileId(id);
    }

    @Override
    public Journal get(Long id) throws IllegalArgumentException {
        return journalDao.findById(id).orElseThrow();
    }

    @Override
    public void update(JournalDto journalDto, Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Journal updatedJournal = get(id);
        if (dtUpdate != updatedJournal.getUpdateDate()) {
            throw new OptimisticLockException("Невозможно выполнить обновление, так как дневник " +
                    "приема пищи был изменен");
        } else {
            updatedJournal.setEating(journalDto.getEating());
            updatedJournal.setProfile(journalDto.getProfile());
            updatedJournal.setProduct(journalDto.getProduct());
            updatedJournal.setDish(journalDto.getDish());
            updatedJournal.setMeasure(journalDto.getMeasure());

            LocalDateTime updateDate = LocalDateTime.now();
            updatedJournal.setUpdateDate(updateDate);

            journalDao.save(updatedJournal);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Journal updatedJournal = get(id);
        if (dtUpdate != updatedJournal.getUpdateDate()) {
            throw new OptimisticLockException("Невозможно выполнить удаление, так как дневник " +
                    "приема пищи был изменен");
        } else {
            journalDao.deleteById(id);
        }
    }
}
