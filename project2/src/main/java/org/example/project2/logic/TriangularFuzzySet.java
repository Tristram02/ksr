package org.example.project2.logic;

public class TriangularFuzzySet<T> extends TrapezoidalFuzzySet<T> {

    public TriangularFuzzySet(DoubleExtractorFromType<T> doubleValueExtractor,
                              double a, double b, double c,
                              double universeBegin, double universeEnd) {
        super(doubleValueExtractor, a, b, b, c, universeBegin, universeEnd);
    }
}
