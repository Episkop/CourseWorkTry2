package ua.studert.coursework.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.studert.coursework.Entity.SpendingEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Repository.SpendingRepository;
import ua.studert.coursework.Service.ServiceInterface.SpendingServiceInterface;

import java.util.List;

@Service
public class SpendingService implements SpendingServiceInterface {
    private final SpendingRepository spendingRepository;

    public SpendingService(SpendingRepository spendingRepository) {
        this.spendingRepository = spendingRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public List<SpendingEntity> getAllSpending() throws DBIsEmptyException {
        List<SpendingEntity> list = spendingRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        return list;
    }

    @Transactional
    @Override
    public boolean addSpending(SpendingEntity spendingEntity) throws AlreadyExistException {
        if (spendingRepository.existsByArticle(spendingEntity.getArticle()))
            return  false;
        //throw new AlreadyExistException("Such" + profit.getArticle() + "already exist");
        spendingRepository.save(spendingEntity);
        return true;
    }

//    @Transactional
//    @Override
//    public void countLineSum(String article, Double january, Double february, Double march, Double april, Double may,
//                             Double june, Double july, Double august, Double september, Double october, Double november,
//                             Double december){
//    }

    @Transactional(readOnly = true)
    @Override
    public SpendingEntity findByArticle(String article) throws NotFoundException {
        SpendingEntity spending = spendingRepository.findByArticle(article);
        if (spending == null) {
            throw new NotFoundException("Didn`t find article " + spending.getArticle());
        }
        return spending;
    }


//    public String getIfExists(String article) throws NotFoundException {
//        if (profitRepository.existsByArticle(article)) {
//            throw new NotFoundException("Not exist!");
//        }
//        return "true";
//    }

    @Transactional
    @Override
    public boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                                Double june, Double july, Double august, Double september, Double october, Double november,
                                Double december) throws NotFoundException {
        SpendingEntity spending = spendingRepository.findByArticle(article);
        if (spending == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            spending.setJanuary(january);
        if (february != null)
            spending.setFebruary(february);
        if (march != null)
            spending.setMarch(march);
        if (april != null)
            spending.setApril(april);
        if (may != null)
            spending.setMay(may);
        if (june != null)
            spending.setJune(june);
        if (july != null)
            spending.setJuly(july);
        if (august != null)
            spending.setAugust(august);
        if (september != null)
            spending.setSeptember(september);
        if (october != null)
            spending.setOctober(october);
        if (november != null)
            spending.setNovember(november);
        if (december != null)
            spending.setDecember(december);

        //TODO
        spendingRepository.save(spending);
        return true;
    }

    @Transactional
    @Override
    public void deleteSpending(Long id) throws NotFoundException{
        spendingRepository.deleteById(id);
    }
}

