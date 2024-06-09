package org.example.project2.logic.variables.coal;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;

import java.util.List;

public class CoalProd extends Variable<DataEntry> {

    public CoalProd(List<Label> labels) {
        super(VariablesEnum.COAL_PROD.getName(), labels);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getCoalProd();
    }
}
