package by.it_academy.jd2.my_application.services.dataBaseService;

import by.it_academy.jd2.my_application.dao.api.IActiveDao;
import by.it_academy.jd2.my_application.dto.ActiveByDateDto;
import by.it_academy.jd2.my_application.dto.ActiveDto;
import by.it_academy.jd2.my_application.models.Active;
import by.it_academy.jd2.my_application.security.UserHolder;
import by.it_academy.jd2.my_application.services.calculations.api.IActiveByDateService;
import by.it_academy.jd2.my_application.services.dataBaseService.api.IActiveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;

@Service
public class ActiveService implements IActiveService {

    private final IActiveDao activeDao;
    private final IActiveByDateService activeByDateService;
    private final UserHolder userHolder;

    public ActiveService(IActiveDao activeDao, IActiveByDateService activeByDateService, UserHolder userHolder) {
        this.activeDao = activeDao;
        this.activeByDateService = activeByDateService;
        this.userHolder = userHolder;
    }

    @Override
    public Active save(ActiveDto activeDto) {
        Active active = new Active();
        active.setCreator(userHolder.getUser());
        active.setName(activeDto.getName());
        active.setProfile(activeDto.getProfile());
        active.setCalories(activeDto.getCalories());

        LocalDateTime creationDate = LocalDateTime.now().withNano(0);
        active.setCreationDate(creationDate);
        active.setUpdateDate(creationDate);
        activeDao.save(active);
        return active;
    }

    @Override
    public ActiveByDateDto findAllByProfileIdAndCreationDate(LocalDateTime start, LocalDateTime end,
                                                             Long id, Pageable pageable) {
        Page<Active> actives = activeDao.findAllByCreationDateBetweenAndProfileId(start, end, id, pageable);
            return activeByDateService.getActiveByDate(actives);
    }


    @Override
    public Active get(Long id) throws IllegalArgumentException {
        return activeDao.findById(id).orElseThrow();
    }

    @Override
    public void update(ActiveDto activeDto, Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Active updatedActive = get(id);

        if (!updatedActive.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("???????????????????? ???? ?????????? ???????? ??????????????????, ?????? ??????" +
                    " ?????????????????????? ???????????????????? ???????? ????????????????");
        } else {

            updatedActive.setName(activeDto.getName());
            updatedActive.setCalories(activeDto.getCalories());
            updatedActive.setProfile(activeDto.getProfile());

            LocalDateTime updateDate = LocalDateTime.now().withNano(0);
            updatedActive.setUpdateDate(updateDate);

            activeDao.save(updatedActive);
        }
    }

    @Override
    public void delete(Long id, LocalDateTime dtUpdate) throws OptimisticLockException {
        Active deletedActive = get(id);
        if (!deletedActive.getUpdateDate().isEqual(dtUpdate)) {
            throw new OptimisticLockException("???????????????? ???? ?????????? ???????? ??????????????????, ?????? ?????? ?????????????????? " +
                    "???????????????????? ???????? ????????????????");
        } else {
            activeDao.deleteById(id);
        }
    }
}
