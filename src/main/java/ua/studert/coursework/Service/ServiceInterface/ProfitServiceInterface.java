package ua.studert.coursework.Service.ServiceInterface;

import org.springframework.transaction.annotation.Transactional;
import ua.studert.coursework.Entity.ProfitEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;

import java.util.List;

public interface ProfitServiceInterface {
    List<ProfitEntity> getAllProfit () throws DBIsEmptyException;
    boolean addProfit (ProfitEntity profit) throws AlreadyExistException;

    ProfitEntity findByArticle(String article) throws NotFoundException;
    boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
                         Double june, Double july, Double august, Double september, Double october, Double november,
                         Double december, Double sum) throws NotFoundException;

    void deleteProfit (Long id) throws NotFoundException;
}
