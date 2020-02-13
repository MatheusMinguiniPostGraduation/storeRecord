package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.Payment;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.PaymentGreaterThanRecordTotalValueException;
import br.com.minguini.storerecord.repository.PaymentRepository;
import br.com.minguini.storerecord.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    @Autowired
    RecordRepository recordRepository;


    public Payment save(Payment payment) throws PaymentGreaterThanRecordTotalValueException {

        Record record = recordRepository.getOne(payment.getRecord().getId());

        //Updating the record value, adding up what was sold
        if(record != null) {

            if (payment.getTotal() > record.getTotal()){
                throw new PaymentGreaterThanRecordTotalValueException(record);
            }

            Double value = record.getTotal() - payment.getTotal();
            record.setTotal(value);
        }

        return repository.save(payment);
    }

}
