package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
