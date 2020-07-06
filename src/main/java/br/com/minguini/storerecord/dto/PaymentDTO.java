package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Payment;
import br.com.minguini.storerecord.entity.PaymentMethod;
import br.com.minguini.storerecord.util.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class PaymentDTO {

    private Long id;

    private String userName;

    private RecordDTO record;

    private String date;

    private Double value;

    private Double total;

    private PaymentMethod paymentMethod;

    private Boolean cancelled;

    public PaymentDTO(){

    }

    public PaymentDTO(Payment payment){
        this.id = payment.getId();
        this.record = new RecordDTO(payment.getRecord());

        this.date = LocalDateTimeUtil.getFormattedDate(LocalDateTime.ofInstant(payment.getDate().toInstant(),
                ZoneId.systemDefault()));

        this.userName = payment.getUser().getUsername();
        this.value = payment.getValue();
        this.paymentMethod = payment.getMethod();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
