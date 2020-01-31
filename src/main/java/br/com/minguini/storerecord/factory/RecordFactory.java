package br.com.minguini.storerecord.factory;

import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.entity.Record;

import java.time.LocalDateTime;

public class RecordFactory {

    public static Record getRecord(Costumer costumer){
        Record record = new Record();
        record.setCostumer(costumer);
        record.setTotal(0.0);
        record.setCreation_date(LocalDateTime.now());

        return record;
    }
}
