package ua.studert.coursework.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.studert.coursework.Entity.ProfitEntity;

public interface ProfitRepository extends JpaRepository<ProfitEntity, Long> {
    boolean existsByArticle (String article);
    ProfitEntity findByArticle (String article);

//    @Query("select e.march  + e.april as sum from ProfitEntity e where e.article=:salary")
//    Double countByMarchIsNotNullAndAprilNotNull (String article);
    //
//    @Query (value = "SELECT SUM (january, february, march, april, may, june, july, august,\n" +
//            "september, october, november, december) from ProfitEntity where article = ?",nativeQuery = true);
//     Double sum() ;

}