package org.example.project2;

import javafx.application.Application;
import org.example.project2.db.CsvReader;
import org.example.project2.enums.ContinentsEnum;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Quantifier;
import org.example.project2.logic.linguistics.Summary;
import org.example.project2.view.WindowMode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.project2.enums.ContinentsEnum.*;

public class Main {

    static Initialization initialData = new Initialization();

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            Application.launch(WindowMode.class, args);
        } else {
            CsvReader csvReader = new CsvReader("src/main/java/org/example/project2/db/db.csv");
            List<DataEntry> dataEntries = csvReader.readData();

            boolean end = false;
            while (!end) {

                System.out.println("########### PROJECT 2 #########");
                System.out.println("Choose one or multi subject summary: ");
                System.out.println("1. One subject summary");
                System.out.println("2. Multi subject summary");
                String subject = new DataInputStream(System.in).readLine();
                if (subject.equals("1")) {
                    System.out.println("Choose type of summary: ");
                    System.out.println("1. Q data entries are/have Sj [T]");
                    System.out.println("2. Q data entries being/having W are/have Sj [T]");
                    String type = new DataInputStream(System.in).readLine();
                    if (type.equals("1")) {
                        Map<String, ArrayList<Integer>> choices = getChoices(1);
                        Summary summary = new Summary(getChosenQuantifier(choices.get("Quantifier")), null, dataEntries, getChosenSummarizers(choices.get("Summarizers")));
                        List<Double> values = getValuesOfT(summary);
                        System.out.println(summary);
                        printValuesOfT(values);
                    } else {
                        Map<String, ArrayList<Integer>> choices = getChoices(2);
                        Summary summary = new Summary(getChosenQuantifier(choices.get("Quantifier")), getChosenSummarizers(choices.get("Qualifiers")), dataEntries, getChosenSummarizers(choices.get("Summarizers")));
                        List<Double> values = getValuesOfT(summary);
                        System.out.println(summary);
                        printValuesOfT(values);
                    }
                } else if (subject.equals("2")) {
                    System.out.println("Choose type of summary: ");
                    System.out.println("1. Q P1 comparing to P2 are/have Sj [T]");
                    System.out.println("2. Q P1 comparing to P2 that are being/having W are/have Sj [T]");
                    System.out.println("3. Q P1 that are being/having W comparing to P2 are/have Sj [T]");
                    System.out.println("4. More P1 than P2 are/have Sj [T]");
                    String multiSubjectType = new DataInputStream(System.in).readLine();
                    if (multiSubjectType.equals("1")) {
                        Map<String, ArrayList<Integer>> multiChoices = getMultiChoices();
                        Map<String, ArrayList<Integer>> choices = getChoices(1);
                        Summary summary = new Summary(getChosenQuantifier(choices.get("Quantifier")), null, getChosenSubject(multiChoices.get("Subject1"), dataEntries),
                                getChosenSubject(multiChoices.get("Subject2"), dataEntries), getChosenSummarizers(choices.get("Summarizers")),
                                getChosenSubjectName(multiChoices.get("Subject1")), getChosenSubjectName(multiChoices.get("Subject2")), true);
                        System.out.println(summary);
                        System.out.println(STR."T: \{summary.degreeOfTruthMultiType1()}");
                    } else if (multiSubjectType.equals("2")) {
                        Map<String, ArrayList<Integer>> multiChoices = getMultiChoices();
                        Map<String, ArrayList<Integer>> choices = getChoices(2);
                        Summary summary = new Summary(getChosenQuantifier(choices.get("Quantifier")), getChosenSummarizers(choices.get("Qualifiers")), getChosenSubject(multiChoices.get("Subject1"), dataEntries),
                                getChosenSubject(multiChoices.get("Subject2"), dataEntries), getChosenSummarizers(choices.get("Summarizers")),
                                getChosenSubjectName(multiChoices.get("Subject1")), getChosenSubjectName(multiChoices.get("Subject2")), true);
                        System.out.println(summary);
                        System.out.println(STR."T: \{summary.degreeOfTruthMultiType2()}");
                    } else if (multiSubjectType.equals("3")) {
                        Map<String, ArrayList<Integer>> multiChoices = getMultiChoices();
                        Map<String, ArrayList<Integer>> choices = getChoices(2);
                        Summary summary = new Summary(getChosenQuantifier(choices.get("Quantifier")), getChosenSummarizers(choices.get("Qualifiers")), getChosenSubject(multiChoices.get("Subject1"), dataEntries),
                                getChosenSubject(multiChoices.get("Subject2"), dataEntries), getChosenSummarizers(choices.get("Summarizers")),
                                getChosenSubjectName(multiChoices.get("Subject1")), getChosenSubjectName(multiChoices.get("Subject2")), true);
                        System.out.println(summary);
                        System.out.println(STR."T: \{summary.degreeOfTruthMultiType3()}");
                    } else if (multiSubjectType.equals("4")) {
                        Map<String, ArrayList<Integer>> multiChoices = getMultiChoices();
                        Map<String, ArrayList<Integer>> choices = getChoices(3);
                        Summary summary = new Summary(null, null, getChosenSubject(multiChoices.get("Subject1"), dataEntries),
                                getChosenSubject(multiChoices.get("Subject2"), dataEntries), getChosenSummarizers(choices.get("Summarizers")),
                                getChosenSubjectName(multiChoices.get("Subject1")), getChosenSubjectName(multiChoices.get("Subject2")), true);
                        System.out.println(summary);
                        System.out.println(STR."T: \{summary.degreeOfTruthMultiType4()}");
                    }
                }
                System.out.println("Do you want to continue? (y/n)");
                DataInputStream reader = new DataInputStream(System.in);
                String answer = reader.readLine();
                if (answer.equals("n")) {
                    end = true;
                }
            }
        }
    }

    private static String getChosenSubjectName(ArrayList<Integer> subject1) {
        return switch (subject1.getFirst()) {
            case 1 -> AFRICA.getName();
            case 2 -> ASIA.getName();
            case 3 -> EUROPE.getName();
            case 4 -> NORTH_AMERICA.getName();
            case 5 -> OCEANIA.getName();
            case 6 -> SOUTH_AMERICA.getName();
            default -> null;
        };
    }


    private static Map<String, ArrayList<Integer>> getMultiChoices() throws IOException {
        Map<String, ArrayList<Integer>> multiChoicesMap = new HashMap<>();
        DataInputStream reader = new DataInputStream(System.in);
        ArrayList<Integer> subject1Choices = new ArrayList<>();
        ArrayList<Integer> subject2Choices = new ArrayList<>();

        System.out.println("Choose first subject: ");
        System.out.println("1. data entries from Africa");
        System.out.println("2. data entries from Asia");
        System.out.println("3. data entries from Europe");
        System.out.println("4. data entries from North America");
        System.out.println("5. data entries from Oceania");
        System.out.println("6. data entries from South America");
        subject1Choices.add(Integer.parseInt(reader.readLine()));

        System.out.println("Choose second subject: ");
        System.out.println("1. data entries from Africa");
        System.out.println("2. data entries from Asia");
        System.out.println("3. data entries from Europe");
        System.out.println("4. data entries from North America");
        System.out.println("5. data entries from Oceania");
        System.out.println("6. data entries from South America");
        subject2Choices.add(Integer.parseInt(reader.readLine()));

        multiChoicesMap.put("Subject1", subject1Choices);
        multiChoicesMap.put("Subject2", subject2Choices);

        return multiChoicesMap;
    }


    private static Map<String, ArrayList<Integer>> getChoices(int type) throws IOException {
        Map<String, ArrayList<Integer>> choicesMap = new HashMap<>();
        DataInputStream reader = new DataInputStream(System.in);
        ArrayList<Integer> quantifierChoices = new ArrayList<>();
        ArrayList<Integer> summarizerChoices = new ArrayList<>();
        ArrayList<Integer> qualifierChoices = new ArrayList<>();

        if (type != 3) {
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
        }

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

                System.out.println("Do you want to add another qualifier? (y/n): ");
                String response = reader.readLine();
                if (!response.equalsIgnoreCase("y")) {
                    keepGoing = false;
                }
//                keepGoing = false;
            }
        }
        if(type != 3) {
            choicesMap.put("Quantifier", quantifierChoices);
        }
        choicesMap.put("Summarizers", summarizerChoices);
        if (type == 2) {
            choicesMap.put("Qualifiers", qualifierChoices);
        }

        return choicesMap;
    }

    private static Quantifier getChosenQuantifier(ArrayList<Integer> choice) {
        return switch (choice.getFirst()) {
            case 1 -> initialData.getNearlyNone();
            case 2 -> initialData.getAround1_4();
            case 3 -> initialData.getAroundHalf();
            case 4 -> initialData.getAround3_4();
            case 5 -> initialData.getMost();
            case 6 -> initialData.getNearlyAll();
            case 7 -> initialData.getLessThan1000();
            case 8 -> initialData.getAbout2000();
            case 9 -> initialData.getAbout5000();
            case 10 -> initialData.getAbout6000();
            case 11 -> initialData.getOver8000();
            case 12 -> initialData.getOver10000();
            default -> null;
        };

    }

    private static List<DataEntry> getChosenSubject(ArrayList<Integer> subject, List<DataEntry> dataEntries) {
        return switch (subject.getFirst()) {
            case 1 -> dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(AFRICA.getName())).toList();
            case 2 -> dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(ASIA.getName())).toList();
            case 3 -> dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(EUROPE.getName())).toList();
            case 4 -> dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(NORTH_AMERICA.getName())).toList();
            case 5 -> dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(OCEANIA.getName())).toList();
            case 6 -> dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(SOUTH_AMERICA.getName())).toList();
            default -> null;
        };


    }

    private static List<Label> getChosenSummarizers(ArrayList<Integer> choices) {
        List<Label> summarizers = new ArrayList<>();
        for (int choice : choices) {
            switch (choice) {
                case 1 -> summarizers.add((Label) initialData.getCoalAnnChangeProdTwh().getLabels().get(0));
                case 2 -> summarizers.add((Label) initialData.getCoalAnnChangeProdTwh().getLabels().get(1));
                case 3 -> summarizers.add((Label) initialData.getCoalAnnChangeProdTwh().getLabels().get(2));
                case 4 -> summarizers.add((Label) initialData.getCoalProdPerCapita().getLabels().get(0));
                case 5 -> summarizers.add((Label) initialData.getCoalProdPerCapita().getLabels().get(1));
                case 6 -> summarizers.add((Label) initialData.getCoalProdPerCapita().getLabels().get(2));
                case 7 -> summarizers.add((Label) initialData.getCoalProd().getLabels().get(0));
                case 8 -> summarizers.add((Label) initialData.getCoalProd().getLabels().get(1));
                case 9 -> summarizers.add((Label) initialData.getCoalProd().getLabels().get(2));
                case 10 -> summarizers.add((Label) initialData.getOilAnnChangeProdTwh().getLabels().get(0));
                case 11 -> summarizers.add((Label) initialData.getOilAnnChangeProdTwh().getLabels().get(1));
                case 12 -> summarizers.add((Label) initialData.getOilAnnChangeProdTwh().getLabels().get(2));
                case 13 -> summarizers.add((Label) initialData.getOilProdPerCapita().getLabels().get(0));
                case 14 -> summarizers.add((Label) initialData.getOilProdPerCapita().getLabels().get(1));
                case 15 -> summarizers.add((Label) initialData.getOilProdPerCapita().getLabels().get(2));
                case 16 -> summarizers.add((Label) initialData.getOilProd().getLabels().get(0));
                case 17 -> summarizers.add((Label) initialData.getOilProd().getLabels().get(1));
                case 18 -> summarizers.add((Label) initialData.getOilProd().getLabels().get(2));
                case 19 -> summarizers.add((Label) initialData.getGasAnnChangeProdTwh().getLabels().get(0));
                case 20 -> summarizers.add((Label) initialData.getGasAnnChangeProdTwh().getLabels().get(1));
                case 21 -> summarizers.add((Label) initialData.getGasAnnChangeProdTwh().getLabels().get(2));
                case 22 -> summarizers.add((Label) initialData.getGasProdPerCapita().getLabels().get(0));
                case 23 -> summarizers.add((Label) initialData.getGasProdPerCapita().getLabels().get(1));
                case 24 -> summarizers.add((Label) initialData.getGasProdPerCapita().getLabels().get(2));
                case 25 -> summarizers.add((Label) initialData.getGasProd().getLabels().get(0));
                case 26 -> summarizers.add((Label) initialData.getGasProd().getLabels().get(1));
                case 27 -> summarizers.add((Label) initialData.getGasProd().getLabels().get(2));
            }
        }
        return summarizers;
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
        values.add(summary.quality());
        return values;
    }

    public static void printValuesOfT(List<Double> values) {
        System.out.println("Quality: " + values.get(11));
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
