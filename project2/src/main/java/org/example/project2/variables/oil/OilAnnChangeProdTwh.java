package org.example.project2.variables.oil;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Variable;

public class OilAnnChangeProdTwh extends Variable<DataEntry>{



    public OilAnnChangeProdTwh() {
        super(VariablesEnum.ANN_OIL_CHANGE_PROD_TWH.getName());
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getOilChangeProdTwh();
    }
}
