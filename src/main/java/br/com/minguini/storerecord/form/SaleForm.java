package br.com.minguini.storerecord.form;

import br.com.minguini.storerecord.entity.Product;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class SaleForm {

    private Long recordId;

    private Double total;

    @Valid
    private List<ProductForm> products;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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
            product.setUnitValue(productForm.getUnit_value());
            product.setTotalValue(productForm.getTotal_value());
            product.setAmount(productForm.getAmount());
            product.setRemoved(Boolean.FALSE);

            productEntities.add(product);
        });

        return productEntities;
    }
}
