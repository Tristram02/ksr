package org.example.project2.variables.coal;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Variable;

public class CoalAnnChangeProdTwh extends Variable<DataEntry> {



    public CoalAnnChangeProdTwh() {
        super(VariablesEnum.ANN_COAL_CHANGE_PROD_TWH.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getCoalChangeProdTwh();
    }

}
