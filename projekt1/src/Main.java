import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        FilesExtractor filesExtractor = new FilesExtractor("E:\\KSR\\projekt1\\projekt1\\reuters_files", "E:\\KSR\\projekt1\\projekt1\\src\\parsed_files");
//        filesExtractor.extractFiles();

        List<Article> articles;

        CharacteristicsExtractor characteristicsExtractor = new CharacteristicsExtractor("E:\\KSR\\projekt1\\projekt1\\src\\parsed_files");
        articles = characteristicsExtractor.extractCharacteristicsForAllArticles();

        List<Article> experiment = new ArrayList<>(articles.subList(0, 100));

        int splitIndex = (int) (experiment.size() * 0.2);
        List<Article> trainingSet = new ArrayList<>(experiment.subList(0, splitIndex));
        List<Article> testingSet = new ArrayList<>(experiment.subList(splitIndex, experiment.size()));
        int k = 5;
        String metric = "euclidean";
        KNN knn = new KNN(k, metric, trainingSet, testingSet);
        List<Article> predictions = knn.classify();

        int good = 0;

        for (Article prediction : predictions) {
            if (prediction.countryPrediction.equals(prediction.country)) {
                good++;
            } else {
              System.out.println("Expected: " + prediction.country + "\nActual: " + prediction.countryPrediction);
            }
        }

        System.out.println(trainingSet.size());
        System.out.println(testingSet.size());
        System.out.println(good);
    }
}