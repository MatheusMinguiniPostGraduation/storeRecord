package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.entity.Record;

public class RecordDTO {

    private Long id;

    private Costumer costumer;

    private Double total;

    public RecordDTO(Record record) {
       this.id = record.getId();
       this.costumer = record.getCostumer();
       this.total = record.getTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
