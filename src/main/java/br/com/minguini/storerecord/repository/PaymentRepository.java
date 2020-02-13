package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
