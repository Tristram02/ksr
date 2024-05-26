package org.example.project2.variables.gas;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Variable;

public class GasProd extends Variable<DataEntry> {

    public GasProd() {
        super(VariablesEnum.GAS_PROD.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getGasProd();
    }
}
