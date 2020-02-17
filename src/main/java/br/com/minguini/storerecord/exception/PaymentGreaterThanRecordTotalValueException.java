package br.com.minguini.storerecord.exception;

import br.com.minguini.storerecord.entity.Payment;

public class PaymentGreaterThanRecordTotalValueException extends Exception {

    private Payment payment;

    public PaymentGreaterThanRecordTotalValueException(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
