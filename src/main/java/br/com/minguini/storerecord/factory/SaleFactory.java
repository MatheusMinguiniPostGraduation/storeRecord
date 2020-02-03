package br.com.minguini.storerecord.factory;

import br.com.minguini.storerecord.entity.Product;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.entity.User;
import br.com.minguini.storerecord.form.SaleForm;

import java.time.LocalDateTime;

public class SaleFactory {

    public static Sale getSale(SaleForm saleForm){

        Record record = new Record();
        record.setId(saleForm.getRecordId());

        User user = new User();
        user.setId(saleForm.getUserId());

        Sale sale = new Sale();

        sale.setUser(user);
        sale.setRecord(record);
        sale.setDate(LocalDateTime.now());
        sale.setProducts(saleForm.getProducts());

        Double total = 0.0;

        for (Product product : sale.getProducts()){
            total = total + product.getValue();
        }
        sale.setTotal(total);

        return sale;
    }

}
