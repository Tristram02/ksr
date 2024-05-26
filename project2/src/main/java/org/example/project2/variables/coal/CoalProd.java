package org.example.project2.variables.coal;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Variable;

public class CoalProd extends Variable<DataEntry> {

    public CoalProd() {
        super(VariablesEnum.COAL_PROD.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getCoalProd();
    }
}
