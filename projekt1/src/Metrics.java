import java.util.*;

public class Metrics {

    public static double EuclideanDistance(FeaturesVector X, FeaturesVector Y) {
        double result = 0.0;

        for (int i = 0; i < X.size(); i++) {
            if (X.get(i).isNumber()) {
                result += Math.pow(X.get(i).getNumber() - Y.get(i).getNumber(), 2);
            } else if (X.get(i).isBoolean()) {
                result += Math.pow((X.get(i).getValue() ? 1 : 0) - (Y.get(i).getValue() ? 1 : 0), 2);
            } else {
                result += Math.pow(Trigram(X.get(i).getText(), Y.get(i).getText()), 2);
            }
        }

        return Math.sqrt(result);
    }

    public static double ChebyshevDistance(FeaturesVector X, FeaturesVector Y) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < X.size(); i++) {
            if (X.get(i).isNumber()) {
                result.add(Math.abs(X.get(i).getNumber() - Y.get(i).getNumber()));
            } else if (X.get(i).isBoolean()) {
                result.add(Math.abs((X.get(i).getValue() ? 1.0 : 0) - (Y.get(i).getValue() ? 1.0 : 0)));
            } else {
                result.add(Math.abs(Trigram(X.get(i).getText(), Y.get(i).getText())));
            }
        }

        return Collections.max(result);
    }

    public static double TaxicabDistance(FeaturesVector X, FeaturesVector Y) {
        double result = 0.0;

        for (int i = 0; i < X.size(); i++) {
            if (X.get(i).isNumber()) {
                result += Math.abs(X.get(i).getNumber() - Y.get(i).getNumber());
            } else if (X.get(i).isBoolean()) {
                result += Math.abs((X.get(i).getValue() ? 1 : 0) - (Y.get(i).getValue() ? 1 : 0));
            } else {
                result += Math.abs(Trigram(X.get(i).getText(), Y.get(i).getText()));
            }
        }

        return result;
    }

    public static double Trigram(String x, String y) {
        Set<String> ngramsX = new HashSet<>();
        Set<String> ngramsY = new HashSet<>();

        for (int i = 0; i < x.length() - 2; i++) {
            ngramsX.add(x.substring(i, i + 3));
        }
        for (int i = 0; i < y.length() - 2; i++) {
            ngramsY.add(y.substring(i, i + 3));
        }

        Double sum = 0.0;
        for (String substring : ngramsX) {
            if (ngramsY.contains(substring)) {
                sum += 1.0;
            }
        }

        if (!ngramsX.isEmpty()) {
            return 1 - (sum / ngramsX.size());
        } else if (!ngramsY.isEmpty()) {
            return 1 - (sum / ngramsY.size());
        } else {
            return 0;
        }
    }

}
