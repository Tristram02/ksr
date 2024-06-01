package org.example.project2.variables.coal;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;

import java.util.List;

public class CoalProdPerCapita extends Variable<DataEntry> {

    public CoalProdPerCapita(List<Label> labels) {
        super(VariablesEnum.COAL_PROD_PER_CAPITA.getName(), labels);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getCoalProdPerCapita();
    }

}
