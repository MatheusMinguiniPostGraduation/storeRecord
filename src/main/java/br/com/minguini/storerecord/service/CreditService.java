package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.Credit;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CreditService {

    @Autowired
    CreditRepository repository;

    public Credit save(Record record, Sale sale, Double value){
        Credit credit = new Credit();
        credit.setRecord(record);
        credit.setSale(sale);
        credit.setDate(LocalDateTime.now());
        credit.setValue(value * -1);

        return repository.save(credit);
    }

    public List<Credit> findCreditsByRecordId(Long recordId) {
        return repository.findByRecordId(recordId);
    }
}
