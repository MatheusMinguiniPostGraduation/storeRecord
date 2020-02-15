package br.com.minguini.storerecord.service;


import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.form.FormFilter;
import br.com.minguini.storerecord.repository.RecordRepository;
import br.com.minguini.storerecord.repository.SaleRepository;
import br.com.minguini.storerecord.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    RecordRepository recordRepository;

    @Transactional
    // Test if given a record ID that does not exist, the Sale will still be persisted. It cant be persisted with a recordId that is not yet saved
    public Sale save(Sale sale){

        Record record = recordRepository.findById(sale.getRecord().getId()).get();

        //Updating the record value, adding up what was sold
        if(record != null){
            Double value = record.getTotal() + sale.getTotal();
            record.setTotal(value);
            sale.setRecord(record);
        }

        return saleRepository.save(sale);
    }
    public List<Sale> getFilteredList(Long recordId, FormFilter filter){

        LocalDateTime fromDateTime = LocalDateTimeUtil.getLocalDateTimeToLookUp(filter.getFromDate(), " 00:00:00");
        LocalDateTime toDateTime = LocalDateTimeUtil.getLocalDateTimeToLookUp(filter.getToDate(), " 23:59:59");

        return saleRepository.getSalesFromFilter(recordId, fromDateTime, toDateTime, filter.getMinValue(), filter.getMaxValue());
    }

}
