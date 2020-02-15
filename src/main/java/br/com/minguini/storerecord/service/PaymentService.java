package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.Payment;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.PaymentGreaterThanRecordTotalValueException;
import br.com.minguini.storerecord.form.FormFilter;
import br.com.minguini.storerecord.repository.PaymentRepository;
import br.com.minguini.storerecord.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    @Autowired
    RecordRepository recordRepository;


    public Payment save(Payment payment) throws PaymentGreaterThanRecordTotalValueException {

        Record record = recordRepository.findById(payment.getRecord().getId()).get();

        //Updating the record value, adding up what was sold
        if(record != null) {
            if (payment.getTotal() > record.getTotal()){
                throw new PaymentGreaterThanRecordTotalValueException(record);
            }

            Double value = record.getTotal() - payment.getTotal();
            record.setTotal(value);

            //Need to set the object in order to be managed by the JPA Entity Manager
            payment.setRecord(record);
        }

        return repository.save(payment);
    }

    public List<Payment> getFilteredList(Long recordId, FormFilter filter){

        Date fromDateTime = null;
        if(filter.getFromDate() != null){
            fromDateTime = Date.valueOf(filter.getFromDate());
        }

        Date toDateTime = null;
        if(toDateTime != null){
            toDateTime = Date.valueOf(filter.getToDate());
        }

        return repository.getPaymentsFromFilter(recordId, fromDateTime, toDateTime, filter.getMinValue(), filter.getMaxValue());
    }

}
