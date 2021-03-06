package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.RecordAlreadyExistsException;
import br.com.minguini.storerecord.form.RecordForm;
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

    public List<Record> findAll(){
        return recordRepository.findAll();
    }

    public Optional<Record> findById(long recordId) {
        return recordRepository.findById(recordId);
    }

    public List<Record> findByCostumerNameOrLastName (String name){
       return recordRepository.findByCostumerNameOrLastName(name);
    }


    public void verifyRecordAlreadyExists(Costumer costumer) throws RecordAlreadyExistsException{
        List<Record> records = recordRepository.findByCostumerNameAndLastName(costumer.getName(), costumer.getLastName());

        if(records.size() > 0){
          throw new RecordAlreadyExistsException(records.get(0));
        }
    }

    public Record update(Long id, RecordForm form) {
        Record record = recordRepository.getOne(id);

        Costumer costumer = record.getCostumer();
        costumer.setName(form.getCostumer().getName());
        costumer.setLastName(form.getCostumer().getLastName());
        costumer.setCpf(form.getCostumer().getCpf());
        costumer.setCellphone(form.getCostumer().getCellphone());
        costumer.setExtraInformation(form.getCostumer().getExtraInformation());

        record.setCostumer(costumer);

        return record;
    }
}
