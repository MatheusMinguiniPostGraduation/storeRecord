package br.com.minguini.storerecord.service;


import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.entity.User;
import br.com.minguini.storerecord.enums.CreditEnum;
import br.com.minguini.storerecord.form.FormFilter;
import br.com.minguini.storerecord.form.SaleForm;
import br.com.minguini.storerecord.repository.RecordRepository;
import br.com.minguini.storerecord.repository.SaleRepository;
import br.com.minguini.storerecord.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CreditService creditService;

    @Transactional
    // Test if given a record ID that does not exist, the Sale will still be persisted. It cant be persisted with a recordId that is not yet saved
    public Sale save(Sale sale){

        Record record = recordRepository.getOne(sale.getRecord().getId());

        if(record.getTotal() < 0){
            persistCreditUsage(record, sale);
        }

        //Updating the record value, adding up what was sold
        if(record != null){
            Double value = record.getTotal() + sale.getTotal();
            record.setTotal(value);
            sale.setRecord(record);
        }

        return saleRepository.save(sale);
    }

    public Sale update(Long id, SaleForm form){
        Sale sale = saleRepository.getOne(id);

        sale.getProducts().clear();
        saleRepository.flush();

        sale.setProducts(form.getProductEntities());

        return sale;
    }

    private void persistCreditUsage(Record record, Sale sale){
        Double totalCredit = record.getTotal() * -1;

        Double totalCreditUsage = 0.0;

        if( (totalCredit - sale.getTotal())  < 0){
            //Means that the credit value is less than the sold value
            totalCreditUsage = record.getTotal();
        }else{
             // Means that the credit card will not be used completely
            totalCreditUsage = sale.getTotal();
        }

        creditService.save(record, sale, totalCreditUsage, CreditEnum.USED.name());
    }

    public List<Sale> getFilteredList(Long recordId, FormFilter filter){

        LocalDateTime fromDateTime = LocalDateTimeUtil.getLocalDateTimeToLookUp(filter.getFromDate(), " 00:00:00");
        LocalDateTime toDateTime = LocalDateTimeUtil.getLocalDateTimeToLookUp(filter.getToDate(), " 23:59:59");

        return saleRepository.getSalesFromFilter(recordId, fromDateTime, toDateTime, filter.getMinValue(), filter.getMaxValue());
    }

    public Sale getSaleById(Long id) {
        return saleRepository.getFetchedSaleElements(id);
    }

    public Record deleteLogically(Long recordId, Long userId){
        Optional<Sale> sale = this.saleRepository.findById(recordId);

        if(!sale.isPresent()){
           //throw RecordNotFoundException here
        }

        //Getting the user who deleted the sale
        User userRemoval = new User();
        userRemoval.setId(userId);

        //Deleting Sale logically
        sale.get().setRemoved(Boolean.TRUE);
        sale.get().setRemovalDate(LocalDateTime.now());
        sale.get().setRemovalUser(userRemoval);

        //Updating Record value
        Record record = sale.get().getRecord();
        Double newRecordTotalValue = record.getTotal() - sale.get().getTotal();

        if(newRecordTotalValue < 0){

            // If the total record is already negative, it means that the sale total is what I am being giving as credit
            if(record.getTotal() < 0){
                creditService.save(record, sale.get(), sale.get().getTotal(), CreditEnum.GIVEN.name());
            }else {

                // If the total record is not negative, it means that the new record total is what I am giving as credit
                creditService.save(record, sale.get(), newRecordTotalValue, CreditEnum.GIVEN.name());
            }
        }


        record.setTotal(newRecordTotalValue);


        return record;
    }
}
