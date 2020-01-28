package br.com.minguini.storerecord.service;

import br.com.minguini.storerecord.entity.Costumer;
import br.com.minguini.storerecord.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostumerService {

    @Autowired
    CostumerRepository repository;

    public void save(Costumer costumer){
        repository.save(costumer);
    }

    public List<Costumer> findAllUsers(){
        return repository.findAll();
    }
}
