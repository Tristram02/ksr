package org.example.project2.variables.oil;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;

import java.util.List;

public class OilProd extends Variable<DataEntry> {

    public OilProd(List<Label> labels) {
        super(VariablesEnum.OIL_PROD.getName(), labels);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getOilProd();
    }
}
