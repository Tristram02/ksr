package org.example.project2.logic.variables.gas;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;
import org.example.project2.logic.sets.ClassicSet;

import java.util.List;

public class GasProd extends Variable<DataEntry> {

    public GasProd(List<Label> labels, ClassicSet universeOfDiscourse) {
        super(VariablesEnum.GAS_PROD.getName(), labels, universeOfDiscourse);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getGasProd();
    }
}
