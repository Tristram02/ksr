import enums.CountriesNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String fullPathReutersFilesDir, fullPathArticlesToSaveDir;
        System.out.println("Full path to reuters files directory: ");
        fullPathReutersFilesDir = in.nextLine();
        System.out.println("Full path to directory where you want articles to be saved: ");
        fullPathArticlesToSaveDir = in.nextLine();

        FilesExtractor filesExtractor = new FilesExtractor(fullPathReutersFilesDir, fullPathArticlesToSaveDir);
        filesExtractor.extractFiles();


        List<Article> articles;

        CharacteristicsExtractor characteristicsExtractor = new CharacteristicsExtractor(fullPathArticlesToSaveDir);
        articles = characteristicsExtractor.extractCharacteristicsForAllArticles();

        List<Article> experiment = new ArrayList<>(articles);

        String metric = "euclidean";
        int k = 5;
        double setSize = 0.4;
        String choice = "c";

        while (true) {
            System.out.println("c - continue, e - exit");
            choice = in.nextLine();
            if (choice.equals("c")) {

                System.out.print("Metric: ");
                metric = in.nextLine();

                System.out.print("k: ");
                k = Integer.parseInt(in.nextLine());

                System.out.print("Size of training set in fraction: ");
                setSize = Double.parseDouble(in.nextLine());
                System.out.println("Counting...");
                int splitIndex = (int) (experiment.size() * setSize);
                List<Article> trainingSet = new ArrayList<>(experiment.subList(0, splitIndex));
                List<Article> testingSet = new ArrayList<>(experiment.subList(splitIndex, experiment.size()));
                KNN knn = new KNN(k, metric, trainingSet, testingSet);
                List<Article> predictions = knn.classify();

                Double acc = 0.0;

                int tp_usa = 0, fp_usa = 0, fn_usa = 0;
                int tp_uk = 0, fp_uk = 0, fn_uk = 0;
                int tp_jap = 0, fp_jap = 0, fn_jap = 0;
                int tp_can = 0, fp_can = 0, fn_can = 0;
                int tp_fra = 0, fp_fra = 0, fn_fra = 0;
                int tp_wg = 0, fp_wg = 0, fn_wg = 0;

                double ppv_usa = 0.0;
                double ppv_uk = 0.0;
                double ppv_jap = 0.0;
                double ppv_can = 0.0;
                double ppv_fra = 0.0;
                double ppv_wg = 0.0;

                double tpr_usa = 0.0;
                double tpr_uk = 0.0;
                double tpr_jap = 0.0;
                double tpr_can = 0.0;
                double tpr_fra = 0.0;
                double tpr_wg = 0.0;

                double f1_usa = 0.0;
                double f1_uk = 0.0;
                double f1_jap = 0.0;
                double f1_can = 0.0;
                double f1_fra = 0.0;
                double f1_wg = 0.0;

                for (Article prediction : predictions) {
                    if (prediction.countryPrediction.equals(prediction.country)) {
                        switch (CountriesNames.fromDisplayName(prediction.country)) {
                            case USA:
                                tp_usa++;
                                break;
                            case UK:
                                tp_uk++;
                                break;
                            case FRANCE:
                                tp_fra++;
                                break;
                            case CANADA:
                                tp_can++;
                                break;
                            case JAPAN:
                                tp_jap++;
                                break;
                            case WEST_GERMANY:
                                tp_wg++;
                                break;
                        }
                    } else {
                        System.out.println("Expected: " + prediction.country + "\nActual: " + prediction.countryPrediction);
                        switch (CountriesNames.fromDisplayName(prediction.country)) {
                            case USA:
                                fn_usa++;
                                break;
                            case UK:
                                fn_uk++;
                                break;
                            case FRANCE:
                                fn_fra++;
                                break;
                            case CANADA:
                                fn_can++;
                                break;
                            case JAPAN:
                                fn_jap++;
                                break;
                            case WEST_GERMANY:
                                fn_wg++;
                                break;
                        }
                        switch (CountriesNames.fromDisplayName(prediction.countryPrediction)) {
                            case USA:
                                fp_usa++;
                                break;
                            case UK:
                                fp_uk++;
                                break;
                            case FRANCE:
                                fp_fra++;
                                break;
                            case CANADA:
                                fp_can++;
                                break;
                            case JAPAN:
                                fp_jap++;
                                break;
                            case WEST_GERMANY:
                                fp_wg++;
                                break;
                        }
                    }
                }

                acc = (double)(tp_fra+tp_can+tp_wg+tp_usa+tp_jap+tp_uk) / predictions.size();

                ppv_usa = tp_usa + fp_usa != 0 ? (double) tp_usa / (tp_usa + fp_usa) : 0;
                ppv_uk = tp_uk + fp_uk != 0 ? (double) tp_uk / (tp_uk + fp_uk) : 0;
                ppv_jap = tp_jap + fp_jap != 0 ? (double) tp_jap / (tp_jap + fp_jap) : 0;
                ppv_can = tp_can + fp_can != 0 ? (double) tp_can / (tp_can + fp_can) : 0;
                ppv_fra = tp_fra + fp_fra != 0 ? (double) tp_fra / (tp_fra + fp_fra) : 0;
                ppv_wg = tp_wg + fp_wg != 0 ? (double) tp_wg / (tp_wg + fp_wg) : 0;

                tpr_usa = tp_usa + fn_usa != 0 ? (double) tp_usa / (tp_usa + fn_usa) : 0;
                tpr_uk = tp_uk + fn_uk != 0 ? (double) tp_uk / (tp_uk + fn_uk) : 0;
                tpr_jap = tp_jap + fn_jap != 0 ? (double) tp_jap / (tp_jap + fn_jap) : 0;
                tpr_can = tp_can + fn_can != 0 ? (double) tp_can / (tp_can + fn_can) : 0;
                tpr_fra = tp_fra + fn_fra != 0 ? (double) tp_fra / (tp_fra + fn_fra) : 0;
                tpr_wg = tp_wg + fn_wg != 0 ? (double) tp_wg / (tp_wg + fn_wg) : 0;

                f1_usa = ppv_usa + tpr_usa != 0 ? 2 * (ppv_usa * tpr_usa / (ppv_usa + tpr_usa)) : 0;
                f1_uk = ppv_uk + tpr_uk != 0 ? 2 * (ppv_uk * tpr_uk / (ppv_uk + tpr_uk)) : 0;
                f1_jap = ppv_jap + tpr_jap != 0 ? 2 * (ppv_jap * tpr_jap / (ppv_jap + tpr_jap)) : 0;
                f1_can = ppv_can + tpr_can != 0 ? 2 * (ppv_can * tpr_can / (ppv_can + tpr_can)) : 0;
                f1_fra = ppv_fra + tpr_fra != 0 ? 2 * (ppv_fra * tpr_fra / (ppv_fra + tpr_fra)) : 0;
                f1_wg = ppv_wg + tpr_wg != 0 ? 2 * (ppv_wg * tpr_wg / (ppv_wg + tpr_wg)) : 0;


                System.out.println("Zbiór uczacy: " + trainingSet.size());
                System.out.println("Zbior testowy: " + testingSet.size());
                System.out.println("Dobrze sklasifikowane: " + (tp_fra+tp_can+tp_wg+tp_usa+tp_jap+tp_uk) );
                System.out.println("Dokladnosc: " + acc);

                System.out.println("PPV_USA: " + ppv_usa);
                System.out.println("PPV_UK: " + ppv_uk);
                System.out.println("PPV_FRA: " + ppv_fra);
                System.out.println("PPV_JAP: " + ppv_jap);
                System.out.println("PPV_CAN: " + ppv_can);
                System.out.println("PPV_WG: " + ppv_wg);

                System.out.println("TPR_USA: " + tpr_usa);
                System.out.println("TPR_UK: " + tpr_uk);
                System.out.println("TPR_FRA: " + tpr_fra);
                System.out.println("TPR_JAP: " + tpr_jap);
                System.out.println("TPR_CAN: " + tpr_can);
                System.out.println("TPR_WG: " + tpr_wg);

                System.out.println("F1_USA: " + f1_usa);
                System.out.println("F1_UK: " + f1_uk);
                System.out.println("F1_FRA: " + f1_fra);
                System.out.println("F1_JAP: " + f1_jap);
                System.out.println("F1_CAN: " + f1_can);
                System.out.println("F1_WG: " + f1_wg);

                System.out.println("PPV_Srednia: " + (CountriesNames.WeightUSA * ppv_usa +
                        + CountriesNames.WeightUK * ppv_uk
                        + CountriesNames.WeightJAPAN * ppv_jap
                        + CountriesNames.WeightCANADA * ppv_can
                        + CountriesNames.WeightWEST_GERMANY * ppv_wg
                        + CountriesNames.WeightFRANCE * ppv_fra
                ) / (CountriesNames.WeightUSA + CountriesNames.WeightUK + CountriesNames.WeightCANADA + CountriesNames.WeightFRANCE + CountriesNames.WeightJAPAN + CountriesNames.WeightWEST_GERMANY));

                System.out.println("TPR_Srednia: " + (CountriesNames.WeightUSA * tpr_usa +
                        + CountriesNames.WeightUK * tpr_uk
                        + CountriesNames.WeightJAPAN * tpr_jap
                        + CountriesNames.WeightCANADA * tpr_can
                        + CountriesNames.WeightWEST_GERMANY * tpr_wg
                        + CountriesNames.WeightFRANCE * tpr_fra
                ) / (CountriesNames.WeightUSA + CountriesNames.WeightUK + CountriesNames.WeightCANADA + CountriesNames.WeightFRANCE + CountriesNames.WeightJAPAN + CountriesNames.WeightWEST_GERMANY));

                System.out.println("F1_Srednia: " + (CountriesNames.WeightUSA * f1_usa +
                        + CountriesNames.WeightUK * f1_uk
                        + CountriesNames.WeightJAPAN * f1_jap
                        + CountriesNames.WeightCANADA * f1_can
                        + CountriesNames.WeightWEST_GERMANY * f1_wg
                        + CountriesNames.WeightFRANCE * f1_fra
                ) / (CountriesNames.WeightUSA + CountriesNames.WeightUK + CountriesNames.WeightCANADA + CountriesNames.WeightFRANCE + CountriesNames.WeightJAPAN + CountriesNames.WeightWEST_GERMANY));

            } else {
                break;
            }

        }

    }
}