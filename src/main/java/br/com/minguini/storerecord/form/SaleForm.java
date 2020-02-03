package br.com.minguini.storerecord.form;

import br.com.minguini.storerecord.entity.Product;

import java.util.List;

public class SaleForm {

    private Long recordId;
    private Long userId;

    private List<Product> products;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
