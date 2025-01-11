package org.example.project2.logic.variables.gas;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;
import org.example.project2.logic.sets.ClassicSet;

import java.util.List;

public class GasAnnChangeProdTwh extends Variable<DataEntry>{
    public GasAnnChangeProdTwh(List<Label> labels, ClassicSet universeOfDiscourse) {
        super(VariablesEnum.ANN_GAS_CHANGE_PROD_TWH.getName(), labels, universeOfDiscourse);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getGasChangeProdTwh();
    }
}
