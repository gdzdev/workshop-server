package org.gdzdev.workshop.backend.infrastructure.adapter.repos;

import org.gdzdev.workshop.backend.infrastructure.adapter.entity.PurchasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseJpaRepository extends JpaRepository<PurchasesEntity, Long> {
    Optional<PurchasesEntity> findById(Long id);
    Optional<PurchasesEntity> findByProvider(String provider);
    List<PurchasesEntity> findAllByProvider(String provider);
    Optional<PurchasesEntity> findByCreatedAt(LocalDateTime date);
    Optional<PurchasesEntity> findByDiscount(BigDecimal discount);
    Optional<PurchasesEntity> findByCount(int count);
    Optional<PurchasesEntity> findByTotalPrice(BigDecimal totalPrice);



    @Query(value = "SELECT * FROM purchases ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Optional<PurchasesEntity> findLastPurchase();
    @Query(value = "SELECT * FROM purchases WHERE DATE_FORMAT(created_at, '%Y-%m') = DATE_FORMAT(CURRENT_DATE(), '%Y-%m') ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    List<PurchasesEntity> getLastPurchasesOfCurrentMonth();

    @Query(
            value =  "SELECT * FROM purchases " +
                    "WHERE DATE_FORMAT(created_at, '%Y-%m') = DATE_FORMAT(CURRENT_DATE(),'%Y-%m') " +
                    "AND total_price ( SELECT MAX(total_price) FROM purchases) " +
                    "WHERE DATE_FORMAT(created_at, '%Y-%m') = DATE_FORMAT(CURRENT_DATE(), '%Y-%m')",
            nativeQuery = true 
    )
    List<PurchasesEntity> getPurchasesWithMoreSpellOfYear();

    @Query(
            value = "SELECT * FROM purchases " +
                    "WHERE MONTH(created_at) = ?1 " +
                    "AND YEAR(created_at) = YEAR(CURRENT_DATE()) " +
                    "AND total_price = ( SELECT MAX(total_price) " +
                    "FROM purchases WHERE MONTH(created_at) = ?1 " +
                    "AND YEAR(created_at) = YEAR(CURRENT_DATE()))",
            nativeQuery = true
    )
    List<PurchasesEntity> getPurchasesWithMoreSpellOfOneMonth(int month);
//    List<PurchasesEntity> getPurchasesOfOneYear(int year);
//    List<PurchasesEntity> getPurchasesWithMoreSpell();
//    List<PurchasesEntity> getLastPurchasesByPage(int page);
}
