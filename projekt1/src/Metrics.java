import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Metrics {

    public static double EuclideanDistance(FeaturesVector X, FeaturesVector Y) {
        double result = 0.0;

        for (int i = 0; i < X.size(); i++) {
            if (X.get(i).isNumber()) {
                result += Math.pow(X.get(i).getNumber() - Y.get(i).getNumber(), 2);
            } else {
                result += 0.0; // ngramy
            }
        }

        return Math.sqrt(result);
    }

    public static double ChebyshevDistance(FeaturesVector X, FeaturesVector Y) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < X.size(); i++) {
            if (X.get(i).isNumber()) {
                result.add(Math.abs(X.get(i).getNumber() - Y.get(i).getNumber()));
            } else {
                result.add(0.0);
            }
        }

        return Collections.max(result);
    }

    public static double TaxicabDistance(FeaturesVector X, FeaturesVector Y) {
        double result = 0.0;

        for (int i = 0; i < X.size(); i++) {
            if (X.get(i).isNumber()) {
                result += Math.abs(X.get(i).getNumber() - Y.get(i).getNumber());
            } else {
                result += 0.0;
            }
        }

        return result;
    }

}
