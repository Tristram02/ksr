package org.example.project2.logic.variables.oil;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Variable;
import org.example.project2.logic.sets.ClassicSet;

import java.util.List;

public class OilAnnChangeProdTwh extends Variable<DataEntry>{



    public OilAnnChangeProdTwh(List<Label> labels, ClassicSet universeOfDiscourse) {
        super(VariablesEnum.ANN_OIL_CHANGE_PROD_TWH.getName(), labels, universeOfDiscourse);
    }

    @Override
    public Double extractAttribute(DataEntry object) {
        return object.getOilChangeProdTwh();
    }
}
