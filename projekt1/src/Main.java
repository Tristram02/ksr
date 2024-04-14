import enums.CountriesNames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        FilesExtractor filesExtractor = new FilesExtractor("E:\\KSR\\projekt1\\projekt1\\reuters_files", "E:\\KSR\\projekt1\\projekt1\\src\\parsed_files");
//        filesExtractor.extractFiles();

        List<Article> articles;

        CharacteristicsExtractor characteristicsExtractor = new CharacteristicsExtractor("E:\\KSR\\projekt1\\projekt1\\src\\parsed_files");
        articles = characteristicsExtractor.extractCharacteristicsForAllArticles();

        List<Article> experiment = new ArrayList<>(articles.subList(0, 1000));
        Collections.shuffle(experiment);

        int splitIndex = (int) (experiment.size() * 0.8);
        List<Article> trainingSet = new ArrayList<>(experiment.subList(0, splitIndex));
        List<Article> testingSet = new ArrayList<>(experiment.subList(splitIndex, experiment.size()));
        int k = 10;
        String metric = "euclidean";
        KNN knn = new KNN(k, metric, trainingSet, testingSet);
        List<Article> predictions = knn.classify();

        int good = 0;

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

        acc = (double)good / predictions.size();

        ppv_usa = (double) tp_usa / (tp_usa + fp_usa);
        ppv_uk = (double) tp_uk / (tp_uk + fp_uk);
        ppv_jap = (double) tp_jap / (tp_jap + fp_jap);
        ppv_can = (double) tp_can / (tp_can + fp_can);
        ppv_fra = (double) tp_fra / (tp_fra + fp_fra);
        ppv_wg = (double) tp_wg / (tp_wg + fp_wg);

        tpr_usa = (double) tp_usa / (tp_usa + fn_usa);
        tpr_uk = (double) tp_uk / (tp_uk + fn_uk);
        tpr_jap = (double) tp_jap / (tp_jap + fn_jap);
        tpr_can = (double) tp_can / (tp_can + fn_can);
        tpr_fra = (double) tp_fra / (tp_fra + fn_fra);
        tpr_wg = (double) tp_wg / (tp_wg + fn_wg);

        f1_usa = 2 * (ppv_usa * tpr_usa / (ppv_usa + tpr_usa));
        f1_uk = 2 * (ppv_uk * tpr_uk / (ppv_uk + tpr_uk));
        f1_jap = 2 * (ppv_fra * tpr_fra / (ppv_fra + tpr_fra));
        f1_can = 2 * (ppv_jap * tpr_jap / (ppv_jap + tpr_jap));
        f1_fra = 2 * (ppv_can * tpr_can / (ppv_can + tpr_can));
        f1_wg = 2 * (ppv_wg * tpr_wg / (ppv_wg + tpr_wg));

        System.out.println("Zbiór uczacy: " + trainingSet.size());
        System.out.println("Zbior testowy: " + testingSet.size());
        System.out.println("Dobrze sklasifikowane: " + good);
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

        System.out.println("TPR_Srednia: " + (CountriesNames.WeightUSA * f1_usa +
                        + CountriesNames.WeightUK * f1_uk
                        + CountriesNames.WeightJAPAN * f1_jap
                        + CountriesNames.WeightCANADA * f1_can
                        + CountriesNames.WeightWEST_GERMANY * f1_wg
                        + CountriesNames.WeightFRANCE * f1_fra
        ) / (CountriesNames.WeightUSA + CountriesNames.WeightUK + CountriesNames.WeightCANADA + CountriesNames.WeightFRANCE + CountriesNames.WeightJAPAN + CountriesNames.WeightWEST_GERMANY));


    }
}