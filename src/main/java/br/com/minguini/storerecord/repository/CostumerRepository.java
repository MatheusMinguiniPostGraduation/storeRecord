package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {

}
