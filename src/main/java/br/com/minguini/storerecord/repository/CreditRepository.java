package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByRecordId(Long id);
}
