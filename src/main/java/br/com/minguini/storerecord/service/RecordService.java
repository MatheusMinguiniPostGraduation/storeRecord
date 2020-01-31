package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.RecordAlreadyExistsException;
import br.com.minguini.storerecord.repository.CostumerRepository;
import br.com.minguini.storerecord.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CostumerRepository costumerRepository;

    @Transactional
    public void save(Record record, Costumer costumer) {
        costumerRepository.save(costumer);

        recordRepository.save(record);
    }

    public Optional<Record> findById(long recordId) {
        return recordRepository.findById(recordId);
    }

    public void verifyRecordAlreadyExists(Costumer costumer) throws RecordAlreadyExistsException{
        List<Record> records = recordRepository.findByCostumerNameAndLastName(costumer.getName(), costumer.getLastName());

        if(records.size() > 0){
          throw new RecordAlreadyExistsException(records.get(0));
        }
    }
}
