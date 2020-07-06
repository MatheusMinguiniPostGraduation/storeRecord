package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Payment;
import br.com.minguini.storerecord.entity.PaymentMethod;

public class PaymentMethodDTO {
    private Long id;
    private String description;

    PaymentMethodDTO(PaymentMethod paymentMethod){
        this.id = paymentMethod.getId();
        this.description = paymentMethod.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
