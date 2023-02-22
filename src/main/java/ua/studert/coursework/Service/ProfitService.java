package ua.studert.coursework.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.studert.coursework.Entity.ProfitEntity;
import ua.studert.coursework.Entity.UserEntity;
import ua.studert.coursework.Exception.AlreadyExistException;
import ua.studert.coursework.Exception.DBIsEmptyException;
import ua.studert.coursework.Exception.NotFoundException;
import ua.studert.coursework.Model.ProfitModel;
import ua.studert.coursework.Repository.ProfitRepository;
import ua.studert.coursework.Repository.UserRepository;
import ua.studert.coursework.Service.ServiceInterface.ProfitServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitService implements ProfitServiceInterface {

    private final ProfitRepository profitRepository;
    private final UserRepository userRepository;

    public ProfitService(ProfitRepository profitRepository,
                         UserRepository userRepository) {
        this.profitRepository = profitRepository;
        this.userRepository = userRepository;
    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<ProfitEntity> getAllProfit() throws DBIsEmptyException {
//        List<ProfitEntity> list = profitRepository.findAll();
//        if (list.isEmpty())
//            throw new DBIsEmptyException("Data Base is empty!");
//        return list;
//    }
    @Transactional(readOnly = true)
    @Override
    public List<ProfitModel> getAllProfit() throws DBIsEmptyException {
        List<ProfitModel> modelList = new ArrayList<>();
        List<ProfitEntity> list = profitRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toModel()));
        return modelList;
    }

    @Transactional
    @Override
    public boolean addProfit(ProfitModel profit, String email) throws AlreadyExistException {
        UserEntity user = userRepository.findByEmail(email);
        ProfitEntity profitEntity = ProfitEntity.fromModel(profit);
        if (profitRepository.existsByArticle(profitEntity.getArticle()))
           return  false;
        user.addProfitEntity(profitEntity);
            //throw new AlreadyExistException("Such" + profit.getArticle() + "already exist");
        userRepository.save(user);
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
    public ProfitEntity findByArticle(String article) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Didn`t find article " + profit.getArticle());
        }
        return profit;
    }

//    public ResponseEntity countLine (String article){
//       ProfitEntity entity = profitRepository.findByArticle(article);
//        Double sum = profitRepository.getSumFromMouns(article);
//        entity.setSum(sum);
//        profitRepository.save(entity);
//        List<ProfitEntity>list = profitRepository.findAll();
//        for (ProfitEntity i: list){
//       Double sum = profitRepository.getSumFromMouns(i.getArticle());
//       i.setSum(sum);
//       profitRepository.save(i);
//        }
//        return null;
//    }
    @Transactional
    @Override
    public boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
                                Double june, Double july, Double august, Double september, Double october, Double november,
                                Double december, Double sum) throws NotFoundException {
        ProfitEntity profit = profitRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            profit.setJanuary(january);
        if (february != null)
            profit.setFebruary(february);
        if (march != null)
            profit.setMarch(march);
        if (april != null)
            profit.setApril(april);
        if (may != null)
            profit.setMay(may);
        if (june != null)
            profit.setJune(june);
        if (july != null)
            profit.setJuly(july);
        if (august != null)
            profit.setAugust(august);
        if (september != null)
            profit.setSeptember(september);
        if (october != null)
            profit.setOctober(october);
        if (november != null)
            profit.setNovember(november);
        if (december != null)
            profit.setDecember(december);
        if (sum != null)
            profit.setSum(sum);
        //TODO
        profitRepository.sumOfMonth(sum,march,april,article);
        profitRepository.save(profit);
        return true;
    }

    @Transactional
    @Override
    public void deleteProfit(Long id) throws NotFoundException{
        profitRepository.deleteById(id);
    }


}
