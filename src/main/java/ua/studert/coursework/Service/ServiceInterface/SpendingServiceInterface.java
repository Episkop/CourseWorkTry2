package ua.studert.coursework.Service.ServiceInterface;

import org.springframework.transaction.annotation.Transactional;
import ua.studert.coursework.Entity.SpendingEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;

import java.util.List;

public interface SpendingServiceInterface {
    @Transactional(readOnly = true)
    List<SpendingEntity> getAllSpending() throws DBIsEmptyException;

    @Transactional
    boolean addSpending(SpendingEntity spendingEntity) throws AlreadyExistException;

    @Transactional(readOnly = true)
    SpendingEntity findByArticle(String article) throws NotFoundException;

    @Transactional
    boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                           Double june, Double july, Double august, Double september, Double october, Double november,
                           Double december) throws NotFoundException;

    @Transactional
    void deleteSpending(Long id) throws NotFoundException;
}
