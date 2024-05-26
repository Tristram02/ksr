package org.example.project2.variables;

import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.functions.TriangularFunction;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.functions.GaussianFunction;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.sets.ClassicSet;
import org.example.project2.logic.sets.FuzzySet;
import org.example.project2.variables.oil.OilAnnChangeProdTwh;
import org.example.project2.variables.oil.OilProd;
import org.example.project2.variables.oil.OilProdPerCapita;

import java.util.Arrays;
import java.util.List;

public class VarOilLables {

    OilAnnChangeProdTwh oilAnnChangeProdTwh = new OilAnnChangeProdTwh();
    OilProdPerCapita oilProdPerCapita = new OilProdPerCapita();
    OilProd oilProd = new OilProd();


        public List<Label> labelsOilAnnChangeProdTwh = Arrays.asList(new Label("smallOilAnnualChange", new FuzzySet(new ClassicSet(-3550, 2800), new TrapezoidalFunction(-3550, -3550, -2170, -1370, -3550, -1370)), oilAnnChangeProdTwh),
                new Label("averageOilAnnualChange", new FuzzySet(new ClassicSet(-3550, 2800), new TrapezoidalFunction(-1870, -1370, 770, 1270, -1870, 1270)), oilAnnChangeProdTwh),
        new Label("bigOilAnnualChange", new FuzzySet(new ClassicSet(-3550, 2800), new TrapezoidalFunction(770, 1570, 2800, 2800, 770, 2800)), oilAnnChangeProdTwh));

    public List<Label> labelsOilProdPerCapita = Arrays.asList(new Label("smallOilProdPerCapita", new FuzzySet(new ClassicSet(0, 3493140), new TrapezoidalFunction(0, 0, 646570, 1246570, 0, 1246570)), oilProdPerCapita),
            new Label("averageOilProdPerCapita", new FuzzySet(new ClassicSet(0, 3493140), new TriangularFunction(446570, 1746570, 3046570, 35800, 3046570)), oilProdPerCapita),
            new Label("bigOilProdPerCapita", new FuzzySet(new ClassicSet(0, 3493140), new TrapezoidalFunction(2246570, 2846570, 3493140, 3493140, 2246570, 3493140)), oilProdPerCapita));


    public List<Label> labelsOilProd = Arrays.asList(new Label("smallOilProd", new FuzzySet(new ClassicSet(0, 52200), new TrapezoidalFunction(0, 0, 12270, 20100, 0, 20100)), oilProd),
            new Label("averageOilProd", new FuzzySet(new ClassicSet(0, 52200), new GaussianFunction(26100, 8700)), oilProd),
            new Label("bigOilProd", new FuzzySet(new ClassicSet(0, 52200), new TrapezoidalFunction(32100, 40100, 52200, 52200, 32100, 52200)), oilProd));


    public List<Label> getLabelsOilAnnChangeProdTwh() {
        return labelsOilAnnChangeProdTwh;
    }

    public List<Label> getLabelsOilProdPerCapita() {
        return labelsOilProdPerCapita;
    }

    public List<Label> getLabelsOilProd() {
        return labelsOilProd;
    }



}
