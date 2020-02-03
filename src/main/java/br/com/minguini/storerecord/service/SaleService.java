package br.com.minguini.storerecord.service;


import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.repository.RecordRepository;
import br.com.minguini.storerecord.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    RecordRepository recordRepository;

    @Transactional
    // Test if given a record ID that does not exist, the Sale will still be persisted. It cant be persisted with a recordId that is not yet saved
    public void save(Sale sale){

        Optional<Record> record = recordRepository.findById(sale.getRecord().getId());

        //Updating the record value, adding up what was sold
        if(record.isPresent()){
            record.get().setTotal(sale.getTotal());
        }

        saleRepository.save(sale);
    }
}
