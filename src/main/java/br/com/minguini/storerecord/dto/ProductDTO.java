package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Product;

public class ProductDTO {

    private Long id;

    private String description;

    private Double unit_value;

    private Double total_value;

    private Integer amount;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.description = product.getDescription();
        this.unit_value = product.getUnitValue();
        this.total_value = product.getTotalValue();
        this.amount = product.getAmount();
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
}
