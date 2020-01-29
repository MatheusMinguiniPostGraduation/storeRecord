package br.com.minguini.storerecord.form;

import br.com.minguini.storerecord.entity.Costumer;

import javax.validation.constraints.NotNull;

public class RecordForm {

    @NotNull
    private Long costumerId;

    public Long getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Long costumerId) {
        this.costumerId = costumerId;
    }
}
