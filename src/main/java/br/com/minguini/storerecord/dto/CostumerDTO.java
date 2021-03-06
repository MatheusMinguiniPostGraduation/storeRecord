package br.com.minguini.storerecord.dto;

import br.com.minguini.storerecord.entity.Costumer;

import java.util.ArrayList;
import java.util.List;

public class CostumerDTO {
    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private String cellphone;
    private String extraInformation;

    public CostumerDTO(Costumer costumer) {
        this.id = costumer.getId();
        this.name = costumer.getName();
        this.lastName = costumer.getLastName();
        this.cpf = costumer.getCpf();
        this.cellphone = costumer.getCellphone();
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCellphone() { return cellphone; }

    public void setCellphone(String cellphone) { this.cellphone = cellphone; }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String info) {
        this.extraInformation = info;
    }
}
