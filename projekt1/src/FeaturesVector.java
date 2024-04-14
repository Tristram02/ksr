import java.util.ArrayList;
import java.util.List;

public class FeaturesVector extends ArrayList<Feature> {

    public FeaturesVector() {

    }

    public double calculateDistances(FeaturesVector Y, String metric) {

        for (int i = 0; i < Y.size(); i++) {
            switch (metric) {
                case "euclidean":
                    return Metrics.EuclideanDistance(this, Y);
                case "chebyshev":
                    return Metrics.ChebyshevDistance(this, Y);
                case "taxicab":
                    return Metrics.TaxicabDistance(this, Y);
            }

        }
        return -1;
    }

    public void normalize(FeaturesVector X) {
        
    }

}
