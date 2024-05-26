package org.example.project2.variables.coal;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Variable;

public class CoalProdPerCapita extends Variable<DataEntry> {

    public CoalProdPerCapita() {
        super(VariablesEnum.COAL_PROD_PER_CAPITA.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getCoalProdPerCapita();
    }

}
