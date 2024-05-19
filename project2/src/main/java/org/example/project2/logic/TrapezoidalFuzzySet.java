package org.example.project2.logic;

public class TrapezoidalFuzzySet<T> implements FuzzySet<T> {

    private final DoubleExtractorFromType<T> doubleValueExtractor;
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double rangeBegin;
    private final double rangeEnd;

    public TrapezoidalFuzzySet(DoubleExtractorFromType<T> doubleValueExtractor,
                               double a, double b, double c, double d,
                               double rangeBegin, double rangeEnd) {

        /* This trapez can be wrapped (from the end to the beginning) in any place,
        so firstly we have to normalize it basing on universe range */
        final double range = rangeEnd - rangeBegin;
        if (b < a) {
            b += range;
        }
        if (c < b) {
            c += range;
        }
        if (d < c) {
            d += range;
        }

        /* Now we can initialize fields */
        this.doubleValueExtractor = doubleValueExtractor;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.rangeBegin = rangeBegin;
        this.rangeEnd = rangeEnd;
    }

    @Override
    public double degreeOfMembership(T object) {
        double x = doubleValueExtractor.apply(object);
        if (x < a) {
            x += rangeEnd - rangeBegin;
        }
        if (x > a && x < b) {
            return (x - a) / (b - a);
        } else if (x >= b && x <= c) {
            return 1.0;
        } else if (x > c && x < d) {
            return 1 - (x - c) / (d - c);
        } else {
            return 0.0;
        }
    }

}
