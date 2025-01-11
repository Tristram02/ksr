package org.example.project2.logic.variables.coal;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;
import org.example.project2.logic.sets.ClassicSet;

import java.util.List;

public class CoalProdPerCapita extends Variable<DataEntry> {

    public CoalProdPerCapita(List<Label> labels, ClassicSet universeOfDiscourse) {
        super(VariablesEnum.COAL_PROD_PER_CAPITA.getName(), labels, universeOfDiscourse);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getCoalProdPerCapita();
    }

}
