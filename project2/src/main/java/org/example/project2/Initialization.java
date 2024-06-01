package org.example.project2;

import org.example.project2.logic.functions.GaussianFunction;
import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.functions.TriangularFunction;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Quantifier;
import org.example.project2.logic.linguistics.QuantifierType;
import org.example.project2.logic.sets.ClassicSet;
import org.example.project2.logic.sets.FuzzySet;
import org.example.project2.variables.coal.CoalAnnChangeProdTwh;
import org.example.project2.variables.coal.CoalProd;
import org.example.project2.variables.coal.CoalProdPerCapita;
import org.example.project2.variables.gas.GasAnnChangeProdTwh;
import org.example.project2.variables.gas.GasProd;
import org.example.project2.variables.gas.GasProdPerCapita;
import org.example.project2.variables.oil.OilAnnChangeProdTwh;
import org.example.project2.variables.oil.OilProd;
import org.example.project2.variables.oil.OilProdPerCapita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Initialization {
    CoalAnnChangeProdTwh coalAnnChangeProdTwh = new CoalAnnChangeProdTwh();
    CoalProdPerCapita coalProdPerCapita = new CoalProdPerCapita();
    CoalProd coalProd = new CoalProd();
    GasAnnChangeProdTwh gasAnnChangeProdTwh = new GasAnnChangeProdTwh();
    GasProdPerCapita gasProdPerCapita = new GasProdPerCapita();
    GasProd gasProd = new GasProd();
    OilAnnChangeProdTwh oilAnnChangeProdTwh = new OilAnnChangeProdTwh();
    OilProdPerCapita oilProdPerCapita = new OilProdPerCapita();
    OilProd oilProd = new OilProd();


    public List<Label> labelsCoalAnnChangeProdTwh = Arrays.asList(new Label("smallCoalAnnualChange", new FuzzySet(new ClassicSet(-2445, 3355), new TrapezoidalFunction(-2445, -2445, -745, -345, -2445, -345)), coalAnnChangeProdTwh.getName()),
            new Label("averageCoalAnnualChange", new FuzzySet(new ClassicSet(-2445, 3355), new TrapezoidalFunction(-745, -345, 1255, 1655, -745, 1655)), coalAnnChangeProdTwh.getName()),
            new Label("bigCoalAnnualChange", new FuzzySet(new ClassicSet(-2445, 3355), new TrapezoidalFunction(1245, 1655, 3355, 3355, 1245, 3355)), coalAnnChangeProdTwh.getName()));

    public List<Label> labelsCoalProdPerCapita = Arrays.asList(new Label("smallCoalProdPerCapita", new FuzzySet(new ClassicSet(0, 151665), new TrapezoidalFunction(0, 0, 25800, 45800, 0, 45800)), coalProdPerCapita.getName()),
            new Label("averageCoalProdPerCapita", new FuzzySet(new ClassicSet(0, 151665), new TriangularFunction(35800, 75800, 115800, 35800, 115800)), coalProdPerCapita.getName()),
            new Label("bigCoalProdPerCapita", new FuzzySet(new ClassicSet(0, 151665), new TrapezoidalFunction(105800, 125800, 151665, 151665, 105800, 151665)), coalProdPerCapita.getName()));


    public List<Label> labelsCoalProd = Arrays.asList(new Label("smallCoalProd", new FuzzySet(new ClassicSet(0, 48490), new TrapezoidalFunction(0, 0, 10245, 18245, 0, 18245)), coalProd.getName()),
            new Label("averageCoalProd", new FuzzySet(new ClassicSet(0, 48490), new GaussianFunction(24245, 8082)), coalProd.getName()),
            new Label("bigCoalProd", new FuzzySet(new ClassicSet(0, 48490), new TrapezoidalFunction(30245, 38245, 48490, 48490, 30245, 48490)), coalProd.getName()));

    public List<Label> labelsGasAnnChangeProdTwh = Arrays.asList(new Label("smallGasAnnualChange", new FuzzySet(new ClassicSet(-1150, 2100), new TrapezoidalFunction(-1150, -1150, -475, -75, -1150, -75)), gasAnnChangeProdTwh.getName()),
            new Label("averageGasAnnualChange", new FuzzySet(new ClassicSet(-1150, 2100), new TrapezoidalFunction(-475, -75, 875, 1275, -475, 1275)), gasAnnChangeProdTwh.getName()),
            new Label("bigGasAnnualChange", new FuzzySet(new ClassicSet(-1150, 2100), new TrapezoidalFunction(875, 1275, 2100, 2100, 875, 2100)), gasAnnChangeProdTwh.getName()));

    public List<Label> labelsGasProdPerCapita = Arrays.asList(new Label("smallGasProdPerCapita", new FuzzySet(new ClassicSet(0, 852965), new TrapezoidalFunction(0, 0, 156500, 286500, 0, 286500)), gasProdPerCapita.getName()),
            new Label("averageGasProdPerCapita", new FuzzySet(new ClassicSet(0, 852965), new TriangularFunction(176500, 426500, 676500, 176500, 676500)), gasProdPerCapita.getName()),
            new Label("bigGasProdPerCapita", new FuzzySet(new ClassicSet(0, 852965), new TrapezoidalFunction(566500, 696500, 852965, 852965, 566500, 852965)), gasProdPerCapita.getName()));


    public List<Label> labelsGasProd = Arrays.asList(new Label("smallGasProd", new FuzzySet(new ClassicSet(0, 40535), new TrapezoidalFunction(0, 0, 8300, 15300, 0, 15300)), gasProd.getName()),
            new Label("averageGasProd", new FuzzySet(new ClassicSet(0, 40535), new GaussianFunction(20267, 6756)), gasProd.getName()),
            new Label("bigGasProd", new FuzzySet(new ClassicSet(0, 40535), new TrapezoidalFunction(25300, 32300, 40535, 40535, 25300, 40535)), gasProd.getName()));

    public List<Label> labelsOilAnnChangeProdTwh = Arrays.asList(new Label("smallOilAnnualChange", new FuzzySet(new ClassicSet(-3550, 2800), new TrapezoidalFunction(-3550, -3550, -2170, -1370, -3550, -1370)), oilAnnChangeProdTwh.getName()),
            new Label("averageOilAnnualChange", new FuzzySet(new ClassicSet(-3550, 2800), new TrapezoidalFunction(-1870, -1370, 770, 1270, -1870, 1270)), oilAnnChangeProdTwh.getName()),
            new Label("bigOilAnnualChange", new FuzzySet(new ClassicSet(-3550, 2800), new TrapezoidalFunction(770, 1570, 2800, 2800, 770, 2800)), oilAnnChangeProdTwh.getName()));

    public List<Label> labelsOilProdPerCapita = Arrays.asList(new Label("smallOilProdPerCapita", new FuzzySet(new ClassicSet(0, 3493140), new TrapezoidalFunction(0, 0, 646570, 1246570, 0, 1246570)), oilProdPerCapita.getName()),
            new Label("averageOilProdPerCapita", new FuzzySet(new ClassicSet(0, 3493140), new TriangularFunction(446570, 1746570, 3046570, 35800, 3046570)), oilProdPerCapita.getName()),
            new Label("bigOilProdPerCapita", new FuzzySet(new ClassicSet(0, 3493140), new TrapezoidalFunction(2246570, 2846570, 3493140, 3493140, 2246570, 3493140)), oilProdPerCapita.getName()));


    public List<Label> labelsOilProd = Arrays.asList(new Label("smallOilProd", new FuzzySet(new ClassicSet(0, 52200), new TrapezoidalFunction(0, 0, 12270, 20100, 0, 20100)), oilProd.getName()),
            new Label("averageOilProd", new FuzzySet(new ClassicSet(0, 52200), new GaussianFunction(26100, 8700)), oilProd.getName()),
            new Label("bigOilProd", new FuzzySet(new ClassicSet(0, 52200), new TrapezoidalFunction(32100, 40100, 52200, 52200, 32100, 52200)), oilProd.getName()));


    Quantifier lessThan1000 = new Quantifier("LESS_THAN_1000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(0, 0, 1000, 2000, 0, 2000 )), QuantifierType.ABSOLUTE);
    Quantifier about2000 = new Quantifier("ABOUT_2000", new FuzzySet(new ClassicSet(0, 11067), new TriangularFunction(1000, 2000, 4500,  1000, 4500 )), QuantifierType.ABSOLUTE);
    Quantifier about5000 = new Quantifier("ABOUT_5000", new FuzzySet(new ClassicSet(0, 11067), new TriangularFunction(3000, 5000, 6000,  3000, 6000 )), QuantifierType.ABSOLUTE);
    Quantifier about6000 = new Quantifier("ABOUT_6000", new FuzzySet(new ClassicSet(0, 11067), new GaussianFunction(6000, 1000)), QuantifierType.ABSOLUTE);
    Quantifier over8000 = new Quantifier("OVER_8000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(7000, 8000, 11067, 11067,  7000, 11067 )), QuantifierType.ABSOLUTE);
    Quantifier over10000 = new Quantifier("OVER_10000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(9000, 10000, 11067, 11067, 9000, 11067 )), QuantifierType.ABSOLUTE);

    Quantifier nearlyNone = new Quantifier("NEARLY_NONE", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0, 0, 0.2, 0.3, 0, 0.3 )), QuantifierType.RELATIVE);
    Quantifier around1_4 = new Quantifier("AROUND_1/4", new FuzzySet(new ClassicSet(0, 1), new TriangularFunction(0.1, 0.3, 0.45,  0.1, 0.45 )), QuantifierType.RELATIVE);
    Quantifier aroundHalf = new Quantifier("AROUND_HALF", new FuzzySet(new ClassicSet(0, 1), new GaussianFunction(0.5, 0.17)), QuantifierType.RELATIVE);
    Quantifier around3_4 = new Quantifier("AROUND_3/4", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0.55, 0.6, 0.75, 0.8, 0.55, 0.8 )), QuantifierType.RELATIVE);
    Quantifier most = new Quantifier("MOST", new FuzzySet(new ClassicSet(0, 1), new TriangularFunction(0.7, 0.8, 0.95,  0.7, 0.95 )), QuantifierType.RELATIVE);
    Quantifier nearlyAll = new Quantifier("NEARLY_ALL", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0.85, 0.9, 1, 1, 0.85, 1 )), QuantifierType.RELATIVE);
    List<Quantifier> allQuantifiers = new ArrayList<>(Arrays.asList(lessThan1000, about2000, about5000, about6000, over8000, over10000,
            nearlyNone, around1_4, aroundHalf, around3_4, most, nearlyAll));

    public List<Label> getLabelsCoalAnnChangeProdTwh() {
        return labelsCoalAnnChangeProdTwh;
    }

    public List<Label> getLabelsCoalProdPerCapita() {
        return labelsCoalProdPerCapita;
    }

    public List<Label> getLabelsCoalProd() {
        return labelsCoalProd;
    }

    public List<Label> getLabelsGasAnnChangeProdTwh() {
        return labelsGasAnnChangeProdTwh;
    }

    public List<Label> getLabelsGasProdPerCapita() {
        return labelsGasProdPerCapita;
    }

    public List<Label> getLabelsGasProd() {
        return labelsGasProd;
    }

    public List<Label> getLabelsOilAnnChangeProdTwh() {
        return labelsOilAnnChangeProdTwh;
    }

    public List<Label> getLabelsOilProdPerCapita() {
        return labelsOilProdPerCapita;
    }

    public List<Label> getLabelsOilProd() {
        return labelsOilProd;
    }

    public Quantifier getLessThan1000() {
        return lessThan1000;
    }

    public Quantifier getAbout2000() {
        return about2000;
    }

    public Quantifier getAbout5000() {
        return about5000;
    }

    public Quantifier getAbout6000() {
        return about6000;
    }

    public Quantifier getOver8000() {
        return over8000;
    }

    public Quantifier getOver10000() {
        return over10000;
    }

    public Quantifier getNearlyNone() {
        return nearlyNone;
    }

    public Quantifier getAround1_4() {
        return around1_4;
    }

    public Quantifier getAroundHalf() {
        return aroundHalf;
    }

    public Quantifier getAround3_4() {
        return around3_4;
    }

    public Quantifier getMost() {
        return most;
    }

    public Quantifier getNearlyAll() {
        return nearlyAll;
    }

    public List<Label> getAllLabels() {
        return Stream.of(labelsCoalAnnChangeProdTwh, labelsCoalProdPerCapita, labelsCoalProd,
                labelsOilAnnChangeProdTwh, labelsOilProdPerCapita, labelsOilProd,
                labelsGasAnnChangeProdTwh, labelsGasProdPerCapita, labelsGasProd)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Quantifier> getAllQuantifiers() {
        return allQuantifiers;
    }

    public void addQuantifier(Quantifier q) {
        allQuantifiers.add(q);
    }

}
