package br.com.minguini.storerecord.controller;


import br.com.minguini.storerecord.dto.RecordDTO;
import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.entity.Record;
import br.com.minguini.storerecord.exception.RecordAlreadyExistsException;
import br.com.minguini.storerecord.factory.RecordFactory;
import br.com.minguini.storerecord.form.RecordForm;
import br.com.minguini.storerecord.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    RecordService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{recordId}")
    public ResponseEntity<RecordDTO> findById(@PathVariable final long recordId){

        Optional<Record> record = service.findById(recordId);

        if(record.isPresent()){
            RecordDTO recordDTO = new RecordDTO(record.get());
            return ResponseEntity.ok(recordDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RecordDTO> insert(@RequestBody @Valid RecordForm form, UriComponentsBuilder uriBuilder) {

        Long costumerId = form.getCostumerId();

        try{
            service.verifyRecordAlreadyExists(costumerId);

            Record record = RecordFactory.getRecord(costumerId);
            service.save(record);

            URI uri = uriBuilder.path("/records/{id}").buildAndExpand(record.getId()).toUri();

            return ResponseEntity.created(uri).body(new RecordDTO(record));
        }catch(RecordAlreadyExistsException exception){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(new RecordDTO(exception.getRecord()));
        }
    }
}
