package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Credit;
import br.com.minguini.storerecord.util.LocalDateTimeUtil;

public class CreditDTO {

    private SaleDTO sale;

    private Double value;

    private String date;

    private String hour;

    private String type;

    public CreditDTO(Credit credit) {
        this.sale = new SaleDTO(credit.getSale());
        this.value = credit.getValue();
        this.date = LocalDateTimeUtil.getFormattedDate(credit.getDate());
        this.hour = LocalDateTimeUtil.getFormattedTime(credit.getDate());
        this.type = credit.getType();
    }

    public SaleDTO getSale() {
        return sale;
    }

    public void setSale(SaleDTO sale) {
        this.sale = sale;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
