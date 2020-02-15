package br.com.minguini.storerecord.controller;

import br.com.minguini.storerecord.dto.PaymentDTO;
import br.com.minguini.storerecord.dto.RecordDTO;
import br.com.minguini.storerecord.entity.Payment;
import br.com.minguini.storerecord.exception.PaymentGreaterThanRecordTotalValueException;
import br.com.minguini.storerecord.factory.PaymentFactory;
import br.com.minguini.storerecord.form.FormFilter;
import br.com.minguini.storerecord.form.PaymentForm;
import br.com.minguini.storerecord.service.PaymentService;
import br.com.minguini.storerecord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


        } catch (PaymentGreaterThanRecordTotalValueException exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RecordDTO(exception.getRecord()));
        }

        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaymentDTO(payment));
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET, value = "/{recordId}")
    public List<PaymentDTO> findPayments(FormFilter filter, @PathVariable("recordId") Long recordId){

        List<Payment> list = paymentService.getFilteredList(recordId, filter);

        return list.stream().map(payment -> new PaymentDTO(payment)).collect(Collectors.toList());
    }
}
