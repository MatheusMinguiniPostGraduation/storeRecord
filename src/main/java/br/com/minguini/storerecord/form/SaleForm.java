package br.com.minguini.storerecord.form;

import br.com.minguini.storerecord.entity.Product;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class SaleForm {

    private Long recordId;
    private Long userId;
    private Double total;

    @Valid
    private List<ProductForm> products;

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

    public List<ProductForm> getProducts() {
        return products;
    }

    public void setProducts(List<ProductForm> products) {
        this.products = products;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Product> getProductEntities(){

        List<Product> productEntities = new ArrayList<Product>();

        this.products.forEach((productForm) -> {
            Product product = new Product();
            product.setDescription(productForm.getDescription());
            product.setUnit_value(productForm.getUnit_value());
            product.setTotal_value(productForm.getTotal_value());
            product.setAmount(productForm.getAmount());

            productEntities.add(product);
        });

        return productEntities;
    }
}
