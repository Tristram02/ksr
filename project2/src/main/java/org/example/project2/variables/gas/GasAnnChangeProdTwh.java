package org.example.project2.variables.gas;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;

import java.util.List;

public class GasAnnChangeProdTwh extends Variable<DataEntry>{



    public GasAnnChangeProdTwh(List<Label> labels) {
        super(VariablesEnum.ANN_GAS_CHANGE_PROD_TWH.getName(), labels);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getGasChangeProdTwh();
    }
}
