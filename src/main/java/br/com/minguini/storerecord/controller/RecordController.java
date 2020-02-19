package br.com.minguini.storerecord.controller;


import br.com.minguini.storerecord.dto.CreditDTO;
import br.com.minguini.storerecord.dto.RecordDTO;
import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.entity.Credit;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.RecordAlreadyExistsException;
import br.com.minguini.storerecord.factory.RecordFactory;
import br.com.minguini.storerecord.form.RecordForm;
import br.com.minguini.storerecord.service.CreditService;
import br.com.minguini.storerecord.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    RecordService service;

    @Autowired
    CreditService creditService;

    @RequestMapping(method = RequestMethod.GET, value = "/{recordId}")
    public ResponseEntity<RecordDTO> findById(@PathVariable final long recordId){

        Optional<Record> record = service.findById(recordId);

        if(record.isPresent()){
            RecordDTO recordDTO = new RecordDTO(record.get());
            return ResponseEntity.ok(recordDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<RecordDTO> findRecords(String name){

        List<RecordDTO> response = new ArrayList<>();

        List<Record> records;

        if(name != null){
            records = service.findByCostumerNameOrLastName(name);
        }else{
            records = service.findAll();
        }

        records.forEach( (record) -> {
            RecordDTO dto = new RecordDTO(record);
            response.add(dto);
        });

        return response;
    }

    @PostMapping
    public ResponseEntity<RecordDTO> insert(@RequestBody @Valid RecordForm form, UriComponentsBuilder uriBuilder) {
        try{
            Costumer costumer = form.convertFormIntoCostumer();

            service.verifyRecordAlreadyExists(costumer);

            Record record = RecordFactory.getRecord(costumer);

            service.save(record, costumer);

            URI uri = uriBuilder.path("/records/{id}").buildAndExpand(record.getId()).toUri();

            return ResponseEntity.created(uri).body(new RecordDTO(record));
        }catch(RecordAlreadyExistsException exception){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(new RecordDTO(exception.getRecord()));
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RecordDTO> update(@PathVariable Long id, @RequestBody @Valid RecordForm form, UriComponentsBuilder uriBuilder) {

        if(id == null){
            return ResponseEntity.badRequest().build();
        }

        Record record = this.service.update(id, form);

        return ResponseEntity.ok(new RecordDTO(record));
    }

    @GetMapping("/{id}/credits")
    public List<CreditDTO> getCredits(@PathVariable Long id){

        List<Credit> list = creditService.findCreditsByRecordId(id);

        List<CreditDTO> lista = list.stream().map(credit -> new CreditDTO(credit)).collect(Collectors.toList());

        return lista;

    }
}
