package br.com.minguini.storerecord.exception;

import br.com.minguini.storerecord.entity.Record;

public class PaymentGreaterThanRecordTotalValueException extends Exception {

    private Record record;

    public PaymentGreaterThanRecordTotalValueException(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

}
