package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query("SELECT record FROM Record record JOIN record.costumer costumer WHERE costumer.name LIKE :name AND costumer.lastName LIKE :lastName")
    List<Record> findByCostumerNameAndLastName(@Param("name") String name, @Param("lastName") String last_name);

    @Query("SELECT record FROM Record record JOIN record.costumer costumer WHERE costumer.name LIKE %:name% OR costumer.lastName LIKE %:name%")
    List<Record> findByCostumerNameOrLastName(@Param("name") String name);

    List<Record> findByCostumerId(Long costumerId);

}
