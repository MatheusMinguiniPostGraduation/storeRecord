package br.com.minguini.storerecord.controller;


import br.com.minguini.storerecord.dto.CostumerDTO;
import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/costumers")
public class CustomerController {

    @Autowired
    CostumerService service;

    @GetMapping
    public List<CostumerDTO> findCostumers(String nome, String sobrenome){

        List<Costumer> costumers;

        return  CostumerDTO.convert(service.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{costumerId}")
    public ResponseEntity<CostumerDTO> findById(@PathVariable final long costumerId){

        Optional<Costumer> costumer = service.findById(costumerId);

        if(costumer.isPresent()){
            CostumerDTO costumerDTO = new CostumerDTO(costumer.get());
            return ResponseEntity.ok(costumerDTO);
        }

        return ResponseEntity.notFound().build();
    }

}
