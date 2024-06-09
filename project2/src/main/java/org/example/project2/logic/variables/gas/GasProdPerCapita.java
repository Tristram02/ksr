package org.example.project2.logic.variables.gas;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;

import java.util.List;

public class GasProdPerCapita extends Variable<DataEntry> {

    public GasProdPerCapita(List<Label> labels) {
        super(VariablesEnum.GAS_PROD_PER_CAPITA.getName(), labels);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getGasProdPerCapita();
    }

}
