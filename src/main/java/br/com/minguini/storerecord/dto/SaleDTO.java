package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDTO {

    private Long id;

    private User user;

    private RecordDTO record;

    private String date;

    private String time;

    private Double total;

    private List<ProductDTO> products;

    public SaleDTO(Sale sale){
        this.id = sale.getId();
        this.date = getSaleDate(sale.getDate());
        this.time =  getSaleTime(sale.getDate());
        //this.record = new RecordDTO(sale.getRecord());
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

    private String getSaleTime(LocalDateTime date){
        int hour = date.getHour();
        int minute = date.getMinute();

        return String.valueOf(hour).concat(":").concat(String.valueOf(minute));
    }

    private String getSaleDate(LocalDateTime date){
        String dateStr = "d/m/y";

        String day = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonthValue());
        String year = String.valueOf(date.getYear());
        dateStr = dateStr.replace("d", day).replace("m", month).replace("y", year);

        return dateStr;
    }
}
