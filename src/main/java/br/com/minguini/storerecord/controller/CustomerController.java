package br.com.minguini.storerecord.controller;


import br.com.minguini.storerecord.dto.CostumerDTO;
import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.form.CostumerForm;
import br.com.minguini.storerecord.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/costumers")
public class CustomerController {

    @Autowired
    CostumerService service;

    @GetMapping
    public List<CostumerDTO> findUsers(){

        List<Costumer> costumers;

        return  CostumerDTO.convert(service.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<CostumerDTO> insert(@RequestBody @Valid CostumerForm form, UriComponentsBuilder uriBuilder){

        Costumer costumer = form.convert();

        service.save(costumer);

        URI uri = uriBuilder.path("/costumer/{id}").buildAndExpand(costumer.getId()).toUri();

        return ResponseEntity.created(uri).body(new CostumerDTO(costumer));
    }
}
