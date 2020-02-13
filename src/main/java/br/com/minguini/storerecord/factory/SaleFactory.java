package br.com.minguini.storerecord.factory;

import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.entity.User;
import br.com.minguini.storerecord.form.SaleForm;

import java.time.LocalDateTime;

public class SaleFactory {

    public static Sale getSale(SaleForm saleForm, Long userId){

        Record record = new Record();
        record.setId(saleForm.getRecordId());

        User user = new User();
        user.setId(userId);

        Sale sale = new Sale();

        sale.setUser(user);
        sale.setRecord(record);
        sale.setDate(LocalDateTime.now());
        sale.setProducts(saleForm.getProductEntities());
        sale.setTotal();

        return sale;
    }

}
