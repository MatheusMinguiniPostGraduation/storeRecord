package br.com.minguini.storerecord.factory;

import br.com.minguini.storerecord.entity.Payment;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.entity.User;
import br.com.minguini.storerecord.form.PaymentForm;

public class PaymentFactory {

    public static Payment getPayment(PaymentForm paymentForm, Long userId){

        Record record = new Record();
        record.setId(paymentForm.getRecordId());

        User user = new User();
        user.setId(userId);

        Payment payment = new Payment();

        payment.setUser(user);
        payment.setRecord(record);
        payment.setDate(paymentForm.getDate());
        payment.setCancelled(Boolean.FALSE);
        payment.setValue(paymentForm.getValue());
        payment.setPaymentMethod(paymentForm.getPaymentMethod());

        return payment;
    }
}
