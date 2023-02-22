package ua.studert.coursework.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.studert.coursework.Entity.ProfitEntity;

public interface ProfitRepository extends JpaRepository<ProfitEntity, Long> {
    boolean existsByArticle (String article);
    ProfitEntity findByArticle (String article);
    @Modifying
    @Query("UPDATE ProfitEntity e SET e.sum = e.march + e.april WHERE e.article ='salary'")
    void sumOfMonth(Double sum,Double march, Double april,String article);



}