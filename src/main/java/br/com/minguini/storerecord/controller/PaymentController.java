package br.com.minguini.storerecord.controller;

import br.com.minguini.storerecord.dto.PaymentDTO;
import br.com.minguini.storerecord.dto.RecordDTO;
import br.com.minguini.storerecord.entity.Payment;
import br.com.minguini.storerecord.entity.PaymentMethod;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.PaymentGreaterThanRecordTotalValueException;
import br.com.minguini.storerecord.factory.PaymentFactory;
import br.com.minguini.storerecord.form.FormFilter;
import br.com.minguini.storerecord.form.PaymentForm;
import br.com.minguini.storerecord.service.PaymentService;
import br.com.minguini.storerecord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<PaymentDTO> save(@RequestBody @Valid PaymentForm form, UriComponentsBuilder uriBuilder, @RequestHeader(value="Authorization") String authorizationHeader){

        Long userId = userService.getUserIdAuthenticated(authorizationHeader);

        Payment payment = PaymentFactory.getPayment(form, userId);

        try {
            paymentService.save(payment);

            URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
            return ResponseEntity.created(uri).body(new PaymentDTO(payment));
        } catch (PaymentGreaterThanRecordTotalValueException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PaymentDTO());
        }
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET, value = "/record/{recordId}")
    public List<PaymentDTO> findPayments(FormFilter filter, @PathVariable("recordId") Long recordId){

        List<Payment> list = paymentService.getFilteredList(recordId, filter);

        return list.stream().map(payment -> new PaymentDTO(payment)).collect(Collectors.toList());
    }

    @DeleteMapping("/{paymentId}")
    @Transactional
    public ResponseEntity<RecordDTO> delete(@PathVariable("paymentId") Long paymentId){
        Record record = paymentService.delete(paymentId);
        return ResponseEntity.ok(new RecordDTO(record));
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET, value = "/methods")
    public List<PaymentMethod> findPaymentsByGroup(){
        List<PaymentMethod> paymentMethods =  paymentService.getPaymentMethods();
        return paymentMethods.stream().collect(Collectors.toList());
    }
}
