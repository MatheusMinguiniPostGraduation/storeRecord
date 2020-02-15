package br.com.minguini.storerecord.controller;


import br.com.minguini.storerecord.dto.SaleDTO;
import br.com.minguini.storerecord.entity.Sale;
import br.com.minguini.storerecord.factory.SaleFactory;
import br.com.minguini.storerecord.form.SaleForm;
import br.com.minguini.storerecord.form.SaleFormFilter;
import br.com.minguini.storerecord.service.SaleService;
import br.com.minguini.storerecord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    SaleService service;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<SaleDTO> save(@RequestBody @Valid SaleForm form, UriComponentsBuilder uriBuilder,   @RequestHeader(value="Authorization") String authorizationHeader){

        Long userId = userService.getUserIdAuthenticated(authorizationHeader);

        Sale sale = SaleFactory.getSale(form, userId);

        service.save(sale);

        URI uri = uriBuilder.path("/sales/{id}").buildAndExpand(sale.getId()).toUri();

        return ResponseEntity.created(uri).body(new SaleDTO(sale));
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET, value = "/{recordId}")
    public List<SaleDTO> findRecords(SaleFormFilter filter, @PathVariable("recordId") Long recordId){

        List<Sale> list = service.getFilteredList(recordId, filter);

        return list.stream().map(sale -> new SaleDTO(sale)).collect(Collectors.toList());
    }


}
