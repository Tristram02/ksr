package org.example.project2.variables;

import org.example.project2.logic.*;
import org.example.project2.variables.coal.CoalAnnChangeProdTwh;
import org.example.project2.variables.coal.CoalProd;
import org.example.project2.variables.coal.CoalProdPerCapita;

import java.util.Arrays;
import java.util.List;

public class VarCoalLables {

    CoalAnnChangeProdTwh coalAnnChangeProdTwh = new CoalAnnChangeProdTwh();
    CoalProdPerCapita coalProdPerCapita = new CoalProdPerCapita();
    CoalProd coalProd = new CoalProd();


        public List<Label> labelsCoalAnnChangeProdTwh = Arrays.asList(new Label("smallCoalAnnualChange", new TrapezoidalFuzzySet<DataEntry>(coalAnnChangeProdTwh::extractAttribute, -2445, -2445, -745, -345, -2445, -345), coalAnnChangeProdTwh),
                new Label("averageCoalAnnualChange", new TrapezoidalFuzzySet<DataEntry>(coalAnnChangeProdTwh::extractAttribute, -745, -345, 1255, 1655, -745, 1655), coalAnnChangeProdTwh),
        new Label("bigCoalAnnualChange", new TrapezoidalFuzzySet<DataEntry>(coalAnnChangeProdTwh::extractAttribute, 1245, 1655, 3355, 3355, 1245, 3355), coalAnnChangeProdTwh));

    public List<Label> labelsCoalProdPerCapita = Arrays.asList(new Label("smallCoalProdPerCapita", new TrapezoidalFuzzySet<DataEntry>(coalProdPerCapita::extractAttribute, 0, 0, 25800, 45800, 0, 45800), coalProdPerCapita),
            new Label("averageCoalProdPerCapita", new TriangularFuzzySet<DataEntry>(coalProdPerCapita::extractAttribute, 35800, 75800, 115800, 35800, 115800), coalProdPerCapita),
            new Label("bigCoalProdPerCapita", new TrapezoidalFuzzySet<DataEntry>(coalProdPerCapita::extractAttribute, 105800, 125800, 151665, 151665, 105800, 151665), coalProdPerCapita));


    public List<Label> labelsCoalProd = Arrays.asList(new Label("smallCoalProd", new TrapezoidalFuzzySet<DataEntry>(coalProd::extractAttribute, 0, 0, 10245, 18245, 0, 18245), coalProd),
            new Label("averageCoalProd", new GaussianFuzzySet<DataEntry>(coalProd::extractAttribute, 24245, 8082), coalProd),
            new Label("bigCoalProd", new TrapezoidalFuzzySet<DataEntry>(coalProd::extractAttribute, 30245, 38245, 48490, 48490, 30245, 48490), coalProd));


    public List<Label> getLabelsCoalAnnChangeProdTwh() {
        return labelsCoalAnnChangeProdTwh;
    }

    public List<Label> getLabelsCoalProdPerCapita() {
        return labelsCoalProdPerCapita;
    }

    public List<Label> getLabelsCoalProd() {
        return labelsCoalProd;
    }



}
