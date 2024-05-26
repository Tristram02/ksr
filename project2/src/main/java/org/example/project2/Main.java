package org.example.project2;

import org.example.project2.db.CsvReader;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Quantifier;
import org.example.project2.logic.linguistics.Summary;
import org.example.project2.quantifiers.AbsoluteQuantifiers;
import org.example.project2.quantifiers.RelativeQuantifiers;
import org.example.project2.variables.VarCoalLables;
import org.example.project2.variables.VarGasLables;
import org.example.project2.variables.VarOilLables;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        boolean end = false;
        while (!end) {

            System.out.println("########### PROJECT 2 #########");

            System.out.println("Choose type of summary: ");
            System.out.println("1. Q data entries are/have Sj [T]");
            System.out.println("2. Q data entries being/having W are/have Sj [T]");
            String type = new DataInputStream(System.in).readLine();
            if (type.equals("1")) {
                Map<String, ArrayList<Integer>> choices = getChoices(1);
                Summary summary = new Summary(getChosenQuantifier(choices.get("Quantifier")), null, dataEntries, getChosenSummarizers(choices.get("Summarizers")));
                List<Double> values = getValuesOfT(summary);
                printValuesOfT(values);
            } else {
                Map<String, ArrayList<Integer>> choices = getChoices(2);
                Summary summary = new Summary(getChosenQuantifier(choices.get("Quantifier")), getChosenSummarizers(choices.get("Qualifiers"))[0], dataEntries, getChosenSummarizers(choices.get("Summarizers")));
                List<Double> values = getValuesOfT(summary);
                printValuesOfT(values);
            }

            System.out.println("Do you want to continue? (y/n)");
            DataInputStream reader = new DataInputStream(System.in);
            String answer = reader.readLine();
            if (answer.equals("n")) {
                end = true;
            }
        }

    }

    private static Map<String, ArrayList<Integer>> getChoices(int type) throws IOException {
        Map<String, ArrayList<Integer>> choicesMap = new java.util.HashMap<>();
        DataInputStream reader = new DataInputStream(System.in);
        ArrayList<Integer> quantifierChoices = new ArrayList<>();
        ArrayList<Integer> summarizerChoices = new ArrayList<>();
        ArrayList<Integer> qualifierChoices = new ArrayList<>();

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
        quantifierChoices.add(Integer.parseInt(reader.readLine()));

        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Choose summarizer (Sj): ");
            System.out.println("1. bigCoalAnnualChangeProdTwh");
            System.out.println("2. averageCoalAnnualChangeProdTwh");
            System.out.println("3. smallCoalAnnualChangeProdTwh");
            System.out.println("4. bigCoalProdPerCapita");
            System.out.println("5. averageCoalProdPerCapita");
            System.out.println("6. smallCoalProdPerCapita");
            System.out.println("7. bigCoalProd");
            System.out.println("8. averageCoalProd");
            System.out.println("9. smallCoalProd");
            System.out.println("10. bigOilAnnualChangeProdTwh");
            System.out.println("11. averageOilAnnualChangeProdTwh");
            System.out.println("12. smallOilAnnualChangeProdTwh");
            System.out.println("13. bigOilProdPerCapita");
            System.out.println("14. averageOilProdPerCapita");
            System.out.println("15. smallOilProdPerCapita");
            System.out.println("16. bigOilProd");
            System.out.println("17. averageOilProd");
            System.out.println("18. smallOilProd");
            System.out.println("19. bigGasAnnualChangeProdTwh");
            System.out.println("20. averageGasAnnualChangeProdTwh");
            System.out.println("21. smallGasAnnualChangeProdTwh");
            System.out.println("22. bigGasProdPerCapita");
            System.out.println("23. averageGasProdPerCapita");
            System.out.println("24. smallGasProdPerCapita");
            System.out.println("25. bigGasProd");
            System.out.println("26. averageGasProd");
            System.out.println("27. smallGasProd");
            summarizerChoices.add(Integer.parseInt(reader.readLine()));

            System.out.println("Do you want to add another summarizer? (y/n): ");
            String response = reader.readLine();
            if (!response.equalsIgnoreCase("y")) {
                keepGoing = false;
            }
        }

        if (type == 2) {
            keepGoing = true;
            while (keepGoing) {
                System.out.println("Choose qualifier (W): ");
                System.out.println("1. bigCoalAnnualChangeProdTwh");
                System.out.println("2. averageCoalAnnualChangeProdTwh");
                System.out.println("3. smallCoalAnnualChangeProdTwh");
                System.out.println("4. bigCoalProdPerCapita");
                System.out.println("5. averageCoalProdPerCapita");
                System.out.println("6. smallCoalProdPerCapita");
                System.out.println("7. bigCoalProd");
                System.out.println("8. averageCoalProd");
                System.out.println("9. smallCoalProd");
                System.out.println("10. bigOilAnnualChangeProdTwh");
                System.out.println("11. averageOilAnnualChangeProdTwh");
                System.out.println("12. smallOilAnnualChangeProdTwh");
                System.out.println("13. bigOilProdPerCapita");
                System.out.println("14. averageOilProdPerCapita");
                System.out.println("15. smallOilProdPerCapita");
                System.out.println("16. bigOilProd");
                System.out.println("17. averageOilProd");
                System.out.println("18. smallOilProd");
                System.out.println("19. bigGasAnnualChangeProdTwh");
                System.out.println("20. averageGasAnnualChangeProdTwh");
                System.out.println("21. smallGasAnnualChangeProdTwh");
                System.out.println("22. bigGasProdPerCapita");
                System.out.println("23. averageGasProdPerCapita");
                System.out.println("24. smallGasProdPerCapita");
                System.out.println("25. bigGasProd");
                System.out.println("26. averageGasProd");
                System.out.println("27. smallGasProd");
                qualifierChoices.add(Integer.parseInt(reader.readLine()));

//                System.out.println("Do you want to add another qualifier? (y/n): ");
//                String response = reader.readLine();
//                if (!response.equalsIgnoreCase("y")) {
//                    keepGoing = false;
//                }
                keepGoing = false;
            }
        }

        choicesMap.put("Quantifier", quantifierChoices);
        choicesMap.put("Summarizers", summarizerChoices);
        if (type == 2) {
            choicesMap.put("Qualifiers", qualifierChoices);
        }

        return choicesMap;
    }

    private static Quantifier getChosenQuantifier(ArrayList<Integer> choice) {
        return switch (choice.getFirst()) {
            case 1 -> relativeQuantifiers.getNearlyNone();
            case 2 -> relativeQuantifiers.getAround1_4();
            case 3 -> relativeQuantifiers.getAroundHalf();
            case 4 -> relativeQuantifiers.getAround3_4();
            case 5 -> relativeQuantifiers.getMost();
            case 6 -> relativeQuantifiers.getNearlyAll();
            case 7 -> absoluteQuantifiers.getLessThan1000();
            case 8 -> absoluteQuantifiers.getAbout2000();
            case 9 -> absoluteQuantifiers.getAbout5000();
            case 10 -> absoluteQuantifiers.getAbout6000();
            case 11 -> absoluteQuantifiers.getOver8000();
            case 12 -> absoluteQuantifiers.getOver10000();
            default -> null;
        };

    }

    private static Label[] getChosenSummarizers(ArrayList<Integer> choices) {
        List<Label> summarizers = new ArrayList<>();
        for (int choice : choices) {
            switch (choice) {
                case 1 -> summarizers.add(varCoalLables.getLabelsCoalAnnChangeProdTwh().get(0));
                case 2 -> summarizers.add(varCoalLables.getLabelsCoalAnnChangeProdTwh().get(1));
                case 3 -> summarizers.add(varCoalLables.getLabelsCoalAnnChangeProdTwh().get(2));
                case 4 -> summarizers.add(varCoalLables.getLabelsCoalProdPerCapita().get(0));
                case 5 -> summarizers.add(varCoalLables.getLabelsCoalProdPerCapita().get(1));
                case 6 -> summarizers.add(varCoalLables.getLabelsCoalProdPerCapita().get(2));
                case 7 -> summarizers.add(varCoalLables.getLabelsCoalProd().get(0));
                case 8 -> summarizers.add(varCoalLables.getLabelsCoalProd().get(1));
                case 9 -> summarizers.add(varCoalLables.getLabelsCoalProd().get(2));
                case 10 -> summarizers.add(varOilLables.getLabelsOilAnnChangeProdTwh().get(0));
                case 11 -> summarizers.add(varOilLables.getLabelsOilAnnChangeProdTwh().get(1));
                case 12 -> summarizers.add(varOilLables.getLabelsOilAnnChangeProdTwh().get(2));
                case 13 -> summarizers.add(varOilLables.getLabelsOilProdPerCapita().get(0));
                case 14 -> summarizers.add(varOilLables.getLabelsOilProdPerCapita().get(1));
                case 15 -> summarizers.add(varOilLables.getLabelsOilProdPerCapita().get(2));
                case 16 -> summarizers.add(varOilLables.getLabelsOilProd().get(0));
                case 17 -> summarizers.add(varOilLables.getLabelsOilProd().get(1));
                case 18 -> summarizers.add(varOilLables.getLabelsOilProd().get(2));
                case 19 -> summarizers.add(varGasLables.getLabelsGasAnnChangeProdTwh().get(0));
                case 20 -> summarizers.add(varGasLables.getLabelsGasAnnChangeProdTwh().get(1));
                case 21 -> summarizers.add(varGasLables.getLabelsGasAnnChangeProdTwh().get(2));
                case 22 -> summarizers.add(varGasLables.getLabelsGasProdPerCapita().get(0));
                case 23 -> summarizers.add(varGasLables.getLabelsGasProdPerCapita().get(1));
                case 24 -> summarizers.add(varGasLables.getLabelsGasProdPerCapita().get(2));
                case 25 -> summarizers.add(varGasLables.getLabelsGasProd().get(0));
                case 26 -> summarizers.add(varGasLables.getLabelsGasProd().get(1));
                case 27 -> summarizers.add(varGasLables.getLabelsGasProd().get(2));
            }
        }
        return summarizers.toArray(new Label[0]);
    }

    public static List<Double> getValuesOfT(Summary summary) {
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

    public static void printValuesOfT(List<Double> values) {
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