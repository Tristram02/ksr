package org.example.project2.variables.oil;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.DataEntry;
import org.example.project2.logic.Variable;

public class OilProdPerCapita extends Variable<DataEntry> {

    public OilProdPerCapita() {
        super(VariablesEnum.OIL_PROD_PER_CAPITA.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getOilProdPerCapita();
    }

}
