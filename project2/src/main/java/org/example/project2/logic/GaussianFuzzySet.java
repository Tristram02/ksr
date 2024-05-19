package org.example.project2.logic;

public class GaussianFuzzySet<T> implements FuzzySet<T> {

    private final DoubleExtractorFromType<T> doubleValueExtractor;
    private final double center;
    private final double width;

    public GaussianFuzzySet(DoubleExtractorFromType<T> doubleValueExtractor,
                            double center, double width) {
        this.doubleValueExtractor = doubleValueExtractor;
        this.center = center;
        this.width = width;
    }
    @Override
    public double degreeOfMembership(T x) {
        double value = doubleValueExtractor.apply(x);
        return Math.exp(-(center - value) * (center - value) / (2 * width * width));
    }
}
