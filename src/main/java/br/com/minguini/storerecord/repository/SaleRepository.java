package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    /*@Query("SELECT s FROM Sale s " +
            "JOIN FETCH s.record r " +
            "JOIN FETCH r.costumer c WHERE s.id = (:id)")*/

    //Sale findByIdAndFetchRecord(@Param("id") Long id);
}
