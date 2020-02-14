package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Payment;

import java.util.Date;

public class PaymentDTO {

    private Long id;

    private String userName;

    private RecordDTO record;

    private Date date;

    private Double interest;

    private Double value;

    private Double total;

    private Boolean cancelled;

    public PaymentDTO(Payment payment){
        this.id = payment.getId();
        this.record = new RecordDTO(payment.getRecord());
        this.date = payment.getDate();
        this.userName = payment.getUser().getUsername();
        this.value = payment.getValue();
        this.interest = payment.getInterest();
        this.total = payment.getTotal();
        this.cancelled = payment.isCancelled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String name) {
        this.userName = name;
    }

    public RecordDTO getRecord() {
        return record;
    }

    public void setRecord(RecordDTO record) {
        this.record = record;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
