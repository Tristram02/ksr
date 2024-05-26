package org.example.project2.variables.gas;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Variable;

public class GasProdPerCapita extends Variable<DataEntry> {

    public GasProdPerCapita() {
        super(VariablesEnum.GAS_PROD_PER_CAPITA.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getGasProdPerCapita();
    }

}
