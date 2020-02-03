package br.com.minguini.storerecord.controller;


import br.com.minguini.storerecord.dto.SaleDTO;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.factory.SaleFactory;
import br.com.minguini.storerecord.form.SaleForm;
import br.com.minguini.storerecord.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    SaleService service;

    @PostMapping
    public ResponseEntity<SaleDTO> save(@RequestBody @Valid SaleForm form, UriComponentsBuilder uriBuilder){

            Sale sale = SaleFactory.getSale(form);

            service.save(sale);

            URI uri = uriBuilder.path("/sales/{id}").buildAndExpand(sale.getId()).toUri();

            return ResponseEntity.created(uri).body(new SaleDTO());
    }
}
