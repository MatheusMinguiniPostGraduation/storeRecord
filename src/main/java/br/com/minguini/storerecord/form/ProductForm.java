package br.com.minguini.storerecord.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Valid
public class ProductForm {

    private Long id;

    @NotEmpty
    @NotNull
    private String description;

    @Positive
    private Double unit_value;

    @Positive
    private Double total_value;

    @Positive
    private Integer amount;

    private Boolean removed;

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

    public Double getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(Double unit_value) {
        this.unit_value = unit_value;
    }

    public Double getTotal_value() {
        return total_value;
    }

    public void setTotal_value(Double total_value) {
        this.total_value = total_value;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean isRemoved() { return removed; }

    public void setRemoved(Boolean removed) { this.removed = removed; }
}
