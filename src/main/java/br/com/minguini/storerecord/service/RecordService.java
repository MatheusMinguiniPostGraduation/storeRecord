package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.RecordAlreadyExistsException;
import br.com.minguini.storerecord.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    RecordRepository repository;

    public void save(Record record) {
        repository.save(record);
    }

    public Optional<Record> findById(long recordId) {
        return repository.findById(recordId);
    }

    public void verifyRecordAlreadyExists(Long costumerId) throws RecordAlreadyExistsException{
        List<Record> records = repository.findByCostumerId(costumerId);

        if(records.size() > 0){
          throw new RecordAlreadyExistsException(records.get(0));
        }
    }
}
