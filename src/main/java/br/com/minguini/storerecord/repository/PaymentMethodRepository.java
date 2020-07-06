package br.com.minguini.storerecord.repository;

import br.com.minguini.storerecord.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("SELECT payment_method FROM Payment payment JOIN payment.method payment_method ")
    List<PaymentMethod> getPaymentsByGroup();
}
