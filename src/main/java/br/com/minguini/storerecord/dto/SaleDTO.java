package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.util.LocalDateTimeUtil;

import java.util.List;
import java.util.stream.Collectors;

public class SaleDTO {

    private Long id;

    private String userName;

    private RecordDTO record;

    private String date;

    private String time;

    private Double total;

    private List<ProductDTO> products;

    public SaleDTO(Sale sale){
        this.id = sale.getId();
        this.date = LocalDateTimeUtil.getFormattedDate(sale.getDate());

        if(sale.getProducts() != null){
            this.products = sale.getProducts().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
        }

        this.userName = sale.getUser().getUsername();
        this.time =  LocalDateTimeUtil.getFormattedTime(sale.getDate());
        this.record = new RecordDTO(sale.getRecord());
        this.total = sale.getTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RecordDTO getRecord() {
        return record;
    }

    public void setRecord(RecordDTO record) {
        this.record = record;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
