package br.com.minguini.storerecord.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sale {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record record;

    private LocalDateTime date;

    private Double total;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name ="sale_id")
    private List<Product> products;

    private boolean removed;

    @Column(name = "removal_date")
    private LocalDateTime removalDate;

    @ManyToOne
    @JoinColumn(name = "user_removal_id")
    private User removalUser;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotal() { return total; }

    public void setTotal() {
        this.total = this.products.stream().mapToDouble(Product::getTotalValue).sum();
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public LocalDateTime getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(LocalDateTime removalDate) {
        this.removalDate = removalDate;
    }

    public User getRemovalUser() { return removalUser; }

    public void setRemovalUser(User removalUser) { this.removalUser = removalUser; }
}
