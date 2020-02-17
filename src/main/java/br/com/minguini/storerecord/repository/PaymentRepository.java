package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p  " +
            " JOIN FETCH p.user" +
            " WHERE p.record.id = :recordId" +
            " AND ( :from = null OR p.date >= :from ) " +
            " AND ( :to = null OR p.date <= :to )  " +
            " AND ( :minValue = null OR p.value >= :minValue ) " +
            " AND ( :maxValue = null OR p.value <= :maxValue ) ")
    List<Payment> getPaymentsFromFilter(@Param("recordId") Long recordId, @Param("from") Date from, @Param("to")
            Date to, @Param("minValue") Double minValue, @Param("maxValue") Double maxValue);


}
