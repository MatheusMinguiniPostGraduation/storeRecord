package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Product;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDTO {

    private Long id;

    private User user;

    private Record record;

    private LocalDateTime date;

    private Double total;

    private List<Product> products;

    public SaleDTO(Sale sale){
        this.id = sale.getId();
        this.date =  sale.getDate();
        //this.products = sale.getProducts();
        this.record = sale.getRecord();
        this.total = sale.getTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
