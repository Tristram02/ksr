package org.example.project2.variables.oil;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.DataEntry;
import org.example.project2.logic.Variable;

public class OilProd extends Variable<DataEntry> {

    public OilProd() {
        super(VariablesEnum.OIL_PROD.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getOilProd();
    }
}
