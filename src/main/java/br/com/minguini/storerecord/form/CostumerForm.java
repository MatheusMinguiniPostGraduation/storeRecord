package br.com.minguini.storerecord.form;

import br.com.minguini.storerecord.entity.Costumer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CostumerForm {

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty @NotNull
    private String lastName;

    private String extraInformation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public Costumer convert() {
        Costumer costumer = new Costumer();
        costumer.setName(this.name);
        costumer.setLastName(this.lastName);
        costumer.setExtraInformation(this.extraInformation);

        return costumer;
    }
}
