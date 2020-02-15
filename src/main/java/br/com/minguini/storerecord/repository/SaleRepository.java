package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s " +
            "JOIN FETCH s.record r " +
            "JOIN FETCH r.costumer c WHERE s.id = (:id)")
    Optional<Sale> findByIdAndFetchRecord(@Param("id") Long id);

    @Query("SELECT s FROM Sale s WHERE s.record.id = :recordId" +
            " AND ( :from = null OR s.date >= :from ) " +
            " AND ( :to = null OR s.date <= :to )  " +
            " AND ( :minValue = null OR s.total >= :minValue ) " +
            " AND ( :maxValue = null OR s.total <= :maxValue ) ")
    List<Sale> getSalesFromFilter(@Param("recordId") Long recordId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to, @Param("minValue") Double minValue, @Param("maxValue") Double maxValue);
}
