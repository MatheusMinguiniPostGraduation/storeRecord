package br.com.minguini.storerecord.form;

import br.com.minguini.storerecord.entity.Costumer;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public class RecordForm {

    @NotNull
    @Valid
    private CostumerForm costumer;

    public CostumerForm getCostumer() {
        return costumer;
    }

    public void setCostumer(CostumerForm costumer) {
        this.costumer = costumer;
    }

    public Costumer convertFormIntoCostumer(){
        Costumer convertedCostumer = new Costumer();

        convertedCostumer.setId(this.costumer.getId());
        convertedCostumer.setName(this.costumer.getName());
        convertedCostumer.setLastName(this.costumer.getLastName());
        convertedCostumer.setExtraInformation(this.costumer.getExtraInformation());

        return convertedCostumer;
    }
}
