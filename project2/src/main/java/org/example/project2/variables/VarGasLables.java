package org.example.project2.variables;

import org.example.project2.logic.*;
import org.example.project2.variables.gas.GasAnnChangeProdTwh;
import org.example.project2.variables.gas.GasProd;
import org.example.project2.variables.gas.GasProdPerCapita;

import java.util.Arrays;
import java.util.List;

public class VarGasLables {

    GasAnnChangeProdTwh gasAnnChangeProdTwh = new GasAnnChangeProdTwh();
    GasProdPerCapita gasProdPerCapita = new GasProdPerCapita();
    GasProd gasProd = new GasProd();


        public List<Label> labelsGasAnnChangeProdTwh = Arrays.asList(new Label("smallGasAnnualChange", new TrapezoidalFuzzySet<DataEntry>(gasAnnChangeProdTwh::extractAttribute, -1150, -1150, -475, -75, -1150, -75), gasAnnChangeProdTwh),
                new Label("averageGasAnnualChange", new TrapezoidalFuzzySet<DataEntry>(gasAnnChangeProdTwh::extractAttribute, -475, -75, 875, 1275, -475, 1275), gasAnnChangeProdTwh),
        new Label("bigGasAnnualChange", new TrapezoidalFuzzySet<DataEntry>(gasAnnChangeProdTwh::extractAttribute, 875, 1275, 2100, 2100, 875, 2100), gasAnnChangeProdTwh));

    public List<Label> labelsGasProdPerCapita = Arrays.asList(new Label("smallGasProdPerCapita", new TrapezoidalFuzzySet<DataEntry>(gasProdPerCapita::extractAttribute, 0, 0, 156500, 286500, 0, 286500), gasProdPerCapita),
            new Label("averageGasProdPerCapita", new TriangularFuzzySet<DataEntry>(gasProdPerCapita::extractAttribute, 176500, 426500, 676500, 176500, 676500), gasProdPerCapita),
            new Label("bigGasProdPerCapita", new TrapezoidalFuzzySet<DataEntry>(gasProdPerCapita::extractAttribute, 566500, 696500, 852965, 852965, 566500, 852965), gasProdPerCapita));


    public List<Label> labelsGasProd = Arrays.asList(new Label("smallGasProd", new TrapezoidalFuzzySet<DataEntry>(gasProd::extractAttribute, 0, 0, 8300, 15300, 0, 15300), gasProd),
            new Label("averageGasProd", new GaussianFuzzySet<DataEntry>(gasProd::extractAttribute, 20267, 6756), gasProd),
            new Label("bigGasProd", new TrapezoidalFuzzySet<DataEntry>(gasProd::extractAttribute, 25300, 32300, 40535, 40535, 25300, 40535), gasProd));


    public List<Label> getLabelsGasAnnChangeProdTwh() {
        return labelsGasAnnChangeProdTwh;
    }

    public List<Label> getLabelsGasProdPerCapita() {
        return labelsGasProdPerCapita;
    }

    public List<Label> getLabelsGasProd() {
        return labelsGasProd;
    }



}
