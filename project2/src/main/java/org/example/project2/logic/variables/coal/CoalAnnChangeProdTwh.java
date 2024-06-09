package org.example.project2.logic.variables.coal;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;

import java.util.List;

public class CoalAnnChangeProdTwh extends Variable<DataEntry> {

    public CoalAnnChangeProdTwh(List<Label> labels) {
        super(VariablesEnum.ANN_COAL_CHANGE_PROD_TWH.getName(), labels);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getCoalChangeProdTwh();
    }

}
