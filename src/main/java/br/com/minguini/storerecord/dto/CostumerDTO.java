package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Costumer;

import java.util.ArrayList;
import java.util.List;

public class CostumerDTO {
    private Long id;
    private String fullName;
    private String extraInformation;

    public CostumerDTO(Costumer costumer) {
        this.id = costumer.getId();
        this.fullName = costumer.getName().concat(" " + costumer.getLastName());
        this.extraInformation = costumer.getExtraInformation();
    }

    public static List<CostumerDTO> convert(List<Costumer> costumers) {

        List<CostumerDTO> costumersConverted = new ArrayList<CostumerDTO>();

        costumers.forEach(costumer -> {
            CostumerDTO costumerDTO = new CostumerDTO(costumer);
            costumersConverted.add(costumerDTO);
        });

        return costumersConverted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String info) {
        this.extraInformation = info;
    }
}
