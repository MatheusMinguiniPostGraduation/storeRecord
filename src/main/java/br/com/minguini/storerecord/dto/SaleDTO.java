package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Product;
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

    private List<ProductDTO> returnedProducts;

    private boolean removed;

    private String removalDate;

    private String removalHour;

    private String userNameRemoval;

    public SaleDTO(Sale sale){
        this.id = sale.getId();
        this.date = LocalDateTimeUtil.getFormattedDate(sale.getDate());

        if(sale.getProducts() != null){
            this.products = getProductList(sale.getProducts(), Boolean.FALSE);
            this.returnedProducts = getProductList(sale.getProducts(), Boolean.TRUE);
        }

        this.userName = sale.getUser().getUsername();
        this.time =  LocalDateTimeUtil.getFormattedTime(sale.getDate());
        this.record = new RecordDTO(sale.getRecord());
        this.total = sale.getTotal();

        if(sale.isRemoved()){
            setRemovedFieldsSaleDTO(this, sale);
        }
    }

    private List<ProductDTO> getProductList(List<Product> products, Boolean returned){
         return products
                 .stream()
                 .filter(product -> product.getRemoved() == returned)
                 .map(ProductDTO::new)
                 .collect(Collectors.toList());
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

    public List<ProductDTO> getReturnedProducts() {
        return returnedProducts;
    }

    public void setReturnedProducts(List<ProductDTO> returnedProducts) {
        this.returnedProducts = returnedProducts;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public String getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(String removalDate) {
        this.removalDate = removalDate;
    }

    public String getRemovalHour() {
        return removalHour;
    }

    public void setRemovalHour(String removalHour) {
        this.removalHour = removalHour;
    }

    public String getUserNameRemoval() {
        return userNameRemoval;
    }

    public void setUserNameRemoval(String userNameRemoval) {
        this.userNameRemoval = userNameRemoval;
    }

    private void setRemovedFieldsSaleDTO(SaleDTO dto, Sale sale){
        dto.removalDate = LocalDateTimeUtil.getFormattedDate(sale.getRemovalDate());
        dto.removalHour = LocalDateTimeUtil.getFormattedTime(sale.getRemovalDate());
        dto.removed = sale.isRemoved();

        if(sale.getRemovalUser() != null){
            dto.userNameRemoval = sale.getRemovalUser().getUsername();
        }

    }
}
