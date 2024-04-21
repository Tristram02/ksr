import enums.CountriesNames;

import java.util.*;

public class KNN {
    int k;
    String metric;
    List<Article> trainingSet;
    List<Article> testSet;


    public KNN(int k, String metric, List<Article> trainingSet, List<Article> testSet) {
        this.k = k;
        this.metric = metric;
        this.trainingSet = trainingSet;
        this.testSet = testSet;
    }

    public List<Article> classify() {

        for (Article testingArticle : this.testSet) {

            Map<CountriesNames, Integer> classification = new HashMap<>();
            Map<Article, Double> objects = new HashMap<>();

            for (Article trainingArticle : this.trainingSet) {
                objects.put(trainingArticle, countryWeight(trainingArticle) * testingArticle.features.normalize(testingArticle.getNumberOfWords()).calculateDistances(
                        trainingArticle.features.normalize(trainingArticle.numberOfWords),
                        this.metric));
            }

            List<Map.Entry<Article, Double>> objectsList = new ArrayList<>(objects.entrySet());
            objectsList.sort(Map.Entry.comparingByValue());
            List<Article> nearestNeighbours = objectsList.subList(0, this.k).stream()
                    .map(Map.Entry::getKey)
                    .toList();

            classification.put(CountriesNames.USA, 0);
            classification.put(CountriesNames.UK, 0);
            classification.put(CountriesNames.CANADA, 0);
            classification.put(CountriesNames.JAPAN, 0);
            classification.put(CountriesNames.FRANCE, 0);
            classification.put(CountriesNames.WEST_GERMANY, 0);

            nearestNeighbours.forEach((vector) -> {
                CountriesNames country = CountriesNames.fromDisplayName(vector.getCountry());
                classification.put(country, classification.getOrDefault(country, 0) + 1);
            });

            testingArticle.setCountryPrediction(classification.entrySet()
                    .stream()
                    .max((article1, article2) -> article1.getValue() > article2.getValue() ? 1 : -1)
                    .get()
                    .getKey()
                    .getDisplayName());
        }

        return this.testSet;

    }

    private Double countryWeight(Article a) {
        switch (CountriesNames.fromDisplayName(a.country)) {
            case USA:
                return CountriesNames.WeightUSA;
            case UK:
                return CountriesNames.WeightUK;
            case FRANCE:
                return CountriesNames.WeightFRANCE;
            case CANADA:
                return CountriesNames.WeightCANADA;
            case JAPAN:
                return CountriesNames.WeightJAPAN;
            case WEST_GERMANY:
                return CountriesNames.WeightWEST_GERMANY;
            default:
                return 1.0;
        }
    }
}
