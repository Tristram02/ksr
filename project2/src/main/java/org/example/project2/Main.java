package org.example.project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project2.db.CsvReader;
import org.example.project2.logic.DataEntry;
import org.example.project2.logic.Label;
import org.example.project2.logic.Quantifier;
import org.example.project2.logic.Summary;
import org.example.project2.quantifiers.AbsoluteQuantifiers;
import org.example.project2.quantifiers.RelativeQuantifiers;
import org.example.project2.variables.VarCoalLables;
import org.example.project2.variables.VarGasLables;
import org.example.project2.variables.VarOilLables;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {//HelloApplication extends Application {

    static AbsoluteQuantifiers absoluteQuantifiers = new AbsoluteQuantifiers();
    static RelativeQuantifiers relativeQuantifiers = new RelativeQuantifiers();
    static VarCoalLables varCoalLables = new VarCoalLables();
    static VarOilLables varOilLables = new VarOilLables();
    static VarGasLables varGasLables = new VarGasLables();

//    @Override
//    public void start(Stage stage) throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
////        Scene scene = new Scene(fxmlLoader.load(), 500, 700);
////        stage.setTitle("Project 2");
////        stage.setScene(scene);
////        stage.show();
//
//    }

    public static void main(String[] args) throws IOException {
//        launch();
        CsvReader csvReader = new CsvReader("src/main/java/org/example/project2/db/db.csv");
        List<DataEntry> dataEntries = csvReader.readData();

        ArrayList<Integer> choices = getChoices();

        Summary summary = new Summary(getChosenQuantifier(choices.get(0)), null, dataEntries, getChosenSummarizer(choices.get(1)));
        List<Double> values = getValuesOfT(summary);
        printValuesOfT(values);
    }

    private static ArrayList<Integer> getChoices() throws IOException {
        ArrayList<Integer> choices = new ArrayList<>();
        DataInputStream reader = new DataInputStream(System.in);
        System.out.println("########### PROJECT 2 #########");
        System.out.println("Q data entries are/have Sj [T]");
        System.out.println("Choose quantifier (Q): ");
        System.out.println("RELATIVE:");
        System.out.println("1. NEARLY_NONE");
        System.out.println("2. AROUND_1/4");
        System.out.println("3. AROUND_HALF");
        System.out.println("4. AROUND_3/4");
        System.out.println("5. MOST");
        System.out.println("6. NEARLY_ALL");
        System.out.println("ABSOLUTE:");
        System.out.println("7. LESS_THAN_1000");
        System.out.println("8. ABOUT_2000");
        System.out.println("9. ABOUT_5000");
        System.out.println("10. ABOUT_6000");
        System.out.println("11. OVER_8000");
        System.out.println("12. OVER_10000");
        choices.add(Integer.parseInt(reader.readLine()));
        System.out.println("Choose summarizer (Sj): ");
        System.out.println("1. COAL_ANNUAL_CHANGE_PROD_TWH");
        System.out.println("2. COAL_PROD_PER_CAPITA");
        System.out.println("3. COAL_PROD");
        System.out.println("4. OIL_ANNUAL_CHANGE_PROD_TWH");
        System.out.println("5. OIL_PROD_PER_CAPITA");
        System.out.println("6. OIL_PROD");
        System.out.println("7. GAS_ANNUAL_CHANGE_PROD_TWH");
        System.out.println("8. GAS_PROD_PER_CAPITA");
        System.out.println("9. GAS_PROD");
        choices.add(Integer.parseInt(reader.readLine()));

        return choices;
    }

    private static Quantifier getChosenQuantifier(int choice) {
        switch (choice) {
            case 1:
                return relativeQuantifiers.getNearlyNone();
            case 2:
                return relativeQuantifiers.getAround1_4();
            case 3:
                return relativeQuantifiers.getAroundHalf();
            case 4:
                return relativeQuantifiers.getAround3_4();
            case 5:
                return relativeQuantifiers.getMost();
            case 6:
                return relativeQuantifiers.getNearlyAll();
            case 7:
                return absoluteQuantifiers.getLessThan1000();
            case 8:
                return absoluteQuantifiers.getAbout2000();
            case 9:
                return absoluteQuantifiers.getAbout5000();
            case 10:
                return absoluteQuantifiers.getAbout6000();
            case 11:
                return absoluteQuantifiers.getOver8000();
            case 12:
                return absoluteQuantifiers.getOver10000();
        }
        return null;
    }

    private static Label getChosenSummarizer(int choice) {
        switch (choice) {
            case 1:
                return varCoalLables.labelsCoalProd.get(0);
            case 2:
                return varCoalLables.labelsCoalProd.get(1);
            case 3:
                return varCoalLables.labelsCoalProd.get(2);
            case 4:
                return varOilLables.labelsOilProd.get(0);
            case 5:
                return varOilLables.labelsOilProd.get(1);
            case 6:
                return varOilLables.labelsOilProd.get(2);
            case 7:
                return varGasLables.labelsGasProd.get(0);
            case 8:
                return varGasLables.labelsGasProd.get(1);
            case 9:
                return varGasLables.labelsGasProd.get(2);
        }
        return null;
    }

    public static List<Double> getValuesOfT(Summary summary){
        List<Double> values = new ArrayList<>();
        values.add(summary.degreeOfTruth());
        values.add(summary.degreeOfImprecision());
        values.add(summary.degreeOfCovering());
        values.add(summary.degreeOfAppropriateness());
        values.add(summary.lengthOfSummary());
        values.add(summary.degreeOfQuantifierImprecision());
        values.add(summary.degreeOfQuantifierCardinality());
        values.add(summary.degreeOfSummarizerCardinality());
        values.add(summary.degreeOfQualifierImprecision());
        values.add(summary.degreeOfQualifierCardinality());
        values.add(summary.lengthOfQualifier());
        return values;
    }

    public static void printValuesOfT(List<Double> values){
        System.out.println("Degree of truth: " + values.get(0));
        System.out.println("Degree of imprecision: " + values.get(1));
        System.out.println("Degree of covering: " + values.get(2));
        System.out.println("Degree of appropriateness: " + values.get(3));
        System.out.println("Length of summary: " + values.get(4));
        System.out.println("Degree of quantifier imprecision: " + values.get(5));
        System.out.println("Degree of quantifier cardinality: " + values.get(6));
        System.out.println("Degree of summarizer cardinality: " + values.get(7));
        System.out.println("Degree of qualifier imprecision: " + values.get(8));
        System.out.println("Degree of qualifier cardinality: " + values.get(9));
        System.out.println("Length of qualifier: " + values.get(10));
    }

}