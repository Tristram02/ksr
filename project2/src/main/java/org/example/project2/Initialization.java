package org.example.project2;

import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.functions.GaussianFunction;
import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.functions.TriangularFunction;
import org.example.project2.logic.linguistics.*;
import org.example.project2.logic.sets.ClassicSet;
import org.example.project2.logic.sets.FuzzySet;
import org.example.project2.logic.variables.coal.CoalAnnChangeProdTwh;
import org.example.project2.logic.variables.coal.CoalProd;
import org.example.project2.logic.variables.coal.CoalProdPerCapita;
import org.example.project2.logic.variables.gas.GasAnnChangeProdTwh;
import org.example.project2.logic.variables.gas.GasProd;
import org.example.project2.logic.variables.gas.GasProdPerCapita;
import org.example.project2.logic.variables.oil.OilAnnChangeProdTwh;
import org.example.project2.logic.variables.oil.OilProd;
import org.example.project2.logic.variables.oil.OilProdPerCapita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initialization {

    public Variable coalAnnChangeProdTwh = new CoalAnnChangeProdTwh(Arrays.asList(new Label("smallCoalAnnualChange", new FuzzySet(new ClassicSet(-1605, 2440), new TrapezoidalFunction(-1605, -1605, -300, -50, -1605, 0)), VariablesEnum.ANN_COAL_CHANGE_PROD_TWH.getName()),
            new Label("averageCoalAnnualChange", new FuzzySet(new ClassicSet(-1605, 2440), new TrapezoidalFunction(-300, 0, 100, 250, -300, 10)), VariablesEnum.ANN_COAL_CHANGE_PROD_TWH.getName()),
            new Label("bigCoalAnnualChange", new FuzzySet(new ClassicSet(-1605, 2440), new TrapezoidalFunction(150, 250, 2440, 2440, 3, 2440)), VariablesEnum.ANN_COAL_CHANGE_PROD_TWH.getName())));

    public Variable coalProdPerCapita = new CoalProdPerCapita(Arrays.asList(new Label("smallCoalProdPerCapita", new FuzzySet(new ClassicSet(0, 151665), new TrapezoidalFunction(0, 0, 100, 200, 0, 200)), VariablesEnum.COAL_PROD_PER_CAPITA.getName()),
            new Label("averageCoalProdPerCapita", new FuzzySet(new ClassicSet(0, 151665), new TriangularFunction(100, 1000, 1200, 100, 1200)), VariablesEnum.COAL_PROD_PER_CAPITA.getName()),
            new Label("bigCoalProdPerCapita", new FuzzySet(new ClassicSet(0, 151665), new TrapezoidalFunction(1100, 1300, 151665, 151665, 1100, 151665)), VariablesEnum.COAL_PROD_PER_CAPITA.getName())));


    public Variable coalProd = new CoalProd(Arrays.asList(new Label("smallCoalProd", new FuzzySet(new ClassicSet(0, 25620), new TrapezoidalFunction(0, 0, 2, 4, 0, 4)), VariablesEnum.COAL_PROD.getName()),
            new Label("averageCoalProd", new FuzzySet(new ClassicSet(0, 25620), new GaussianFunction(101, 33)), VariablesEnum.COAL_PROD.getName()),
            new Label("bigCoalProd", new FuzzySet(new ClassicSet(0, 25620), new TrapezoidalFunction(200, 500, 25620, 25620, 200, 25620)), VariablesEnum.COAL_PROD.getName())));

    public Variable gasAnnChangeProdTwh = new GasAnnChangeProdTwh(Arrays.asList(new Label("smallGasAnnualChange", new FuzzySet(new ClassicSet(-840, 1310), new TrapezoidalFunction(-840, -840, -100, -0.3, -840, -0.3)), VariablesEnum.ANN_GAS_CHANGE_PROD_TWH.getName()),
            new Label("averageGasAnnualChange", new FuzzySet(new ClassicSet(-840, 1310), new TrapezoidalFunction(-50, -0.3, 5, 50, -50, 50)), VariablesEnum.ANN_GAS_CHANGE_PROD_TWH.getName()),
            new Label("bigGasAnnualChange", new FuzzySet(new ClassicSet(-840, 1310), new TrapezoidalFunction(5, 100, 1310, 1310, 5, 1310)), VariablesEnum.ANN_GAS_CHANGE_PROD_TWH.getName())));

    public Variable gasProdPerCapita = new GasProdPerCapita(Arrays.asList(new Label("smallGasProdPerCapita", new FuzzySet(new ClassicSet(0, 853000), new TrapezoidalFunction(0, 0, 80, 100, 0, 100)), VariablesEnum.GAS_PROD_PER_CAPITA.getName()),
            new Label("averageGasProdPerCapita", new FuzzySet(new ClassicSet(0, 853000), new TriangularFunction(90, 300, 10000, 90, 10000)), VariablesEnum.GAS_PROD_PER_CAPITA.getName()),
            new Label("bigGasProdPerCapita", new FuzzySet(new ClassicSet(0, 853000), new TrapezoidalFunction(8000, 10000, 853000, 853000, 8000, 853000)), VariablesEnum.GAS_PROD_PER_CAPITA.getName())));

    public Variable gasProd = new GasProd(Arrays.asList(new Label("smallGasProd", new FuzzySet(new ClassicSet(0, 10000), new TrapezoidalFunction(0, 0, 10, 15, 0, 15)), VariablesEnum.GAS_PROD.getName()),
            new Label("averageGasProd", new FuzzySet(new ClassicSet(0, 10000), new GaussianFunction(257.5, 80.83)), VariablesEnum.GAS_PROD.getName()),
            new Label("bigGasProd", new FuzzySet(new ClassicSet(0, 10000), new TrapezoidalFunction(400, 500, 10000, 10000, 400, 10000)), VariablesEnum.GAS_PROD.getName())));

    public Variable oilAnnChangeProdTwh = new OilAnnChangeProdTwh(Arrays.asList(new Label("smallOilAnnualChange", new FuzzySet(new ClassicSet(-1935, 1110), new TrapezoidalFunction(-1935, -1935, -100, -1, -1935, -1)), VariablesEnum.ANN_OIL_CHANGE_PROD_TWH.getName()),
            new Label("averageOilAnnualChange", new FuzzySet(new ClassicSet(-1935, 1110), new TrapezoidalFunction(-100, -1, 100, 150, -100, 150)), VariablesEnum.ANN_OIL_CHANGE_PROD_TWH.getName()),
            new Label("bigOilAnnualChange", new FuzzySet(new ClassicSet(-1935, 1110), new TrapezoidalFunction(100, 200, 1110, 1110, 100, 1110)), VariablesEnum.ANN_OIL_CHANGE_PROD_TWH.getName())));

    public Variable oilProdPerCapita = new OilProdPerCapita(Arrays.asList(new Label("smallOilProdPerCapita", new FuzzySet(new ClassicSet(0, 816205), new TrapezoidalFunction(0, 0, 500, 700, 0, 700)), VariablesEnum.OIL_PROD_PER_CAPITA.getName()),
            new Label("averageOilProdPerCapita", new FuzzySet(new ClassicSet(0, 816205), new TriangularFunction(600, 10000, 15000, 600, 15000)), VariablesEnum.OIL_PROD_PER_CAPITA.getName()),
            new Label("bigOilProdPerCapita", new FuzzySet(new ClassicSet(0, 816205), new TrapezoidalFunction(12000, 16000, 816205, 816205, 12000, 816205)), VariablesEnum.OIL_PROD_PER_CAPITA.getName())));

    public Variable oilProd = new OilProd(Arrays.asList(new Label("smallOilProd", new FuzzySet(new ClassicSet(0, 8835), new TrapezoidalFunction(0, 0, 30, 50, 0, 50)), VariablesEnum.OIL_PROD.getName()),
            new Label("averageOilProd", new FuzzySet(new ClassicSet(0, 8835), new GaussianFunction(515, 161.67)), VariablesEnum.OIL_PROD.getName()),
            new Label("bigOilProd", new FuzzySet(new ClassicSet(0, 8835), new TrapezoidalFunction(800, 1000, 8835, 8835, 800, 8835)), VariablesEnum.OIL_PROD.getName())));

    Quantifier lessThan1000 = new Quantifier("LESS THAN 1 000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(0, 0, 1000, 2000, 0, 2000 )), QuantifierType.ABSOLUTE);
    Quantifier about2000 = new Quantifier("ABOUT 2 000", new FuzzySet(new ClassicSet(0, 11067), new TriangularFunction(1000, 2000, 4500,  1000, 4500 )), QuantifierType.ABSOLUTE);
    Quantifier about5000 = new Quantifier("ABOUT 5 000", new FuzzySet(new ClassicSet(0, 11067), new TriangularFunction(3000, 5000, 6000,  3000, 6000 )), QuantifierType.ABSOLUTE);
    Quantifier about6000 = new Quantifier("ABOUT 6 000", new FuzzySet(new ClassicSet(0, 11067), new GaussianFunction(6000, 1000)), QuantifierType.ABSOLUTE);
    Quantifier over8000 = new Quantifier("OVER 8 000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(7000, 8000, 11067, 11067,  7000, 11067 )), QuantifierType.ABSOLUTE);
    Quantifier over10000 = new Quantifier("OVER 10 000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(9000, 10000, 11067, 11067, 9000, 11067 )), QuantifierType.ABSOLUTE);

    Quantifier nearlyNone = new Quantifier("NEARLY NONE", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0, 0, 0.2, 0.3, 0, 0.3 )), QuantifierType.RELATIVE);
    Quantifier around1_4 = new Quantifier("AROUND 1/4", new FuzzySet(new ClassicSet(0, 1), new TriangularFunction(0.1, 0.3, 0.45,  0.1, 0.45 )), QuantifierType.RELATIVE);
    Quantifier aroundHalf = new Quantifier("AROUND HALF", new FuzzySet(new ClassicSet(0, 1), new GaussianFunction(0.5, 0.17)), QuantifierType.RELATIVE);
    Quantifier around3_4 = new Quantifier("AROUND 3/4", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0.55, 0.6, 0.75, 0.8, 0.55, 0.8 )), QuantifierType.RELATIVE);
    Quantifier most = new Quantifier("MOST", new FuzzySet(new ClassicSet(0, 1), new TriangularFunction(0.7, 0.8, 0.95,  0.7, 0.95 )), QuantifierType.RELATIVE);
    Quantifier nearlyAll = new Quantifier("NEARLY ALL", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0.85, 0.9, 1, 1, 0.85, 1 )), QuantifierType.RELATIVE);
    List<Quantifier> allQuantifiers = new ArrayList<>(Arrays.asList(nearlyNone, around1_4, aroundHalf, around3_4, most, nearlyAll,
            lessThan1000, about2000, about5000, about6000, over8000, over10000));

    public Variable<DataEntry> getCoalAnnChangeProdTwh() {
        return coalAnnChangeProdTwh;
    }

    public Variable<DataEntry> getCoalProdPerCapita() {
        return coalProdPerCapita;
    }

    public Variable<DataEntry> getCoalProd() {
        return coalProd;
    }

    public Variable<DataEntry> getGasAnnChangeProdTwh() {
        return gasAnnChangeProdTwh;
    }

    public Variable<DataEntry> getGasProdPerCapita() {
        return gasProdPerCapita;
    }

    public Variable<DataEntry> getGasProd() {
        return gasProd;
    }

    public Variable<DataEntry> getOilAnnChangeProdTwh() {
        return oilAnnChangeProdTwh;
    }

    public Variable<DataEntry> getOilProdPerCapita() {
        return oilProdPerCapita;
    }

    public Variable<DataEntry> getOilProd() {
        return oilProd;
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

    List<Variable> allVariables = new ArrayList<>(Arrays.asList(coalAnnChangeProdTwh, coalProdPerCapita, coalProd,
            oilAnnChangeProdTwh, oilProdPerCapita, oilProd,
            gasAnnChangeProdTwh, gasProdPerCapita, gasProd));

    public List<Variable> getAllVariables() {
        return allVariables;
    }

    public List<Quantifier> getAllQuantifiers() {
        return allQuantifiers;
    }

    public void addQuantifier(Quantifier q) {
        allQuantifiers.add(q);
    }

}
