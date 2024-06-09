package org.example.project2.logic.variables.oil;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;

import java.util.List;

public class OilProdPerCapita extends Variable<DataEntry> {

    public OilProdPerCapita(List<Label> labels) {
        super(VariablesEnum.OIL_PROD_PER_CAPITA.getName(), labels);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getOilProdPerCapita();
    }

}
