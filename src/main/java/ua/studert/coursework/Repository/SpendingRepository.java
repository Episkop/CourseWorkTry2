package ua.studert.coursework.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.studert.coursework.Entity.SpendingEntity;

public interface SpendingRepository extends JpaRepository<SpendingEntity, Long> {

    boolean existsByArticle (String article);
    SpendingEntity findByArticle (String article);
}