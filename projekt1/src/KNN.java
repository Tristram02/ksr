import java.util.*;
import java.util.stream.Collectors;

public class KNN {
    int k;
    String metric;
    List<FeaturesVector> trainingSet;
    List<FeaturesVector> testSet;


    public KNN(int k, String metric, List<FeaturesVector> trainingSet, List<FeaturesVector> testSet) {
        this.k = k;
        this.metric = metric;
        this.trainingSet = trainingSet;
        this.testSet = testSet;
    }

    public void classify() {
        Map<String, Integer> classification = new HashMap<>();
        Map<FeaturesVector, Double> objects = new HashMap<>();

        for (FeaturesVector testingVector : this.testSet) {
            for (FeaturesVector trainingVector : this.trainingSet) {
                objects.put(testingVector, testingVector.calculateDistances(trainingVector, this.metric));
            }
        }

        List<Map.Entry<FeaturesVector, Double>> objectsList = new ArrayList<>(objects.entrySet());
        objectsList.sort(Map.Entry.comparingByValue());

        List<FeaturesVector> nearestNeighbours = objectsList.subList(0, this.k).stream()
                .map(Map.Entry::getKey)
                .toList();

        nearestNeighbours.forEach((vector) -> {
            // Tu zalezy jak zdefiniujemy dokument
            // Ale bierzemy wszystkie k sasiadow i dodajemy do classification
            // Nastepnie zwrocimy najczesciej pojawiajacy sie element (kraj)
//            String place = vector.
        });
    }


}
