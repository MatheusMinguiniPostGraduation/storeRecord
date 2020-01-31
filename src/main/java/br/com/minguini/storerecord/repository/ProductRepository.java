package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
