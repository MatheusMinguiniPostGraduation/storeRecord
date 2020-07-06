package br.com.minguini.storerecord.controller;

import br.com.minguini.storerecord.entity.PaymentMethod;
import br.com.minguini.storerecord.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    @RequestMapping(method = RequestMethod.GET, value = "/payments/methods")
    public List<PaymentMethod> findPaymentsByGroup(){
        return paymentService.getPaymentsByGroup();
    }

}
