package org.example.project2.logic;

import java.util.Arrays;
import java.util.List;

public class Summary<T> {
    private final Quantifier quantifier;
    private final Label<T> qualifier;
    private final Label<T>[] summarizers;
    private final FuzzySet<T> fuzzySetOfCompoundSummarizer;
    private final List<T> objects;

    public Summary(Quantifier quantifier, Label<T> qualifier, List<T> objects,
                             Label<T>... summarizers) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizers = summarizers;
        FuzzySet<T> tmpFuzzySet = summarizers[0].getFuzzySet();
        for (int i = 1; i < summarizers.length; i++) {
            tmpFuzzySet = tmpFuzzySet.and(summarizers[i].getFuzzySet());
        }
        this.fuzzySetOfCompoundSummarizer = tmpFuzzySet;
        this.objects = objects;
    }

    /* T1 */
    public double degreeOfTruth() {
        /* If quantifier is absolute, it has to be the first form of linguistic summary */
//        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
//            return quantifier.compatibilityLevel(fuzzySetOfCompoundSummarizer.cardinality(objects));
//        }
//        /* If quantifier is relative, calculate as for the first, or the second form */
//        else {
//            return quantifier
//                    .compatibilityLevel(fuzzySetOfCompoundSummarizer
//                            .and(qualifier.getFuzzySet())
//                            .cardinality(objects) / qualifier
//                            .getFuzzySet().cardinality(objects));
//        }
        return 0.0;
    }

    /* T2 */
    public double degreeOfImprecision() {
        return 1.0 - Arrays.stream(summarizers)
                .map(Label::getFuzzySet)
                .mapToDouble(fuzzySet -> fuzzySet.degreeOfFuzziness(objects))
                .reduce(1.0, (a, b) -> a * b);
    }

    /* T3 */
    public double degreeOfCovering() {
        return fuzzySetOfCompoundSummarizer.and(qualifier.getFuzzySet())
                .support(objects)
                .size() / (float) qualifier
                .getFuzzySet().support(objects).size();
    }

    /* T4 */
    public double degreeOfAppropriateness() {
        return Math.abs(Arrays.stream(summarizers)
                .mapToDouble(summarizer -> summarizer.getFuzzySet()
                        .support(objects)
                        .size() / (double) objects.size())
                .reduce(1.0, (a, b) -> a * b) - degreeOfCovering());
    }

    /* T5 */
    public double lengthOfSummary() {
        return 2.0 * Math.pow(0.5, summarizers.length);
    }

    /* T6 */
    public double degreeOfQuantifierImprecision() {
//        if (quantifier.getFuzzySet() instanceof TrapezoidalFuzzySet) {
//            TrapezoidalFuzzySet<Double> quantifierFuzzySet =
//                    (TrapezoidalFuzzySet<Double>) quantifier.getFuzzySet();
//            double continuousSupportLength = quantifierFuzzySet.getD() - quantifierFuzzySet.getA();
//            if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
//                return 1.0 - continuousSupportLength / objects.size();
//            } else {
//                return 1.0 - continuousSupportLength;
//            }
//        }
        return 0.0;
    }

    /* T7 */
    public double degreeOfQuantifierCardinality() {
//        if (quantifier.getFuzzySet() instanceof TrapezoidalFuzzySet) {
//            TrapezoidalFuzzySet<Double> quantifierFuzzySet =
//                    (TrapezoidalFuzzySet<Double>) quantifier.getFuzzySet();
//            final double a = quantifierFuzzySet.getA(), b = quantifierFuzzySet.getB(), c =
//                    quantifierFuzzySet.getC(),
//                    d = quantifierFuzzySet.getD();
//            double measure = (b - a) * 0.5 + (c - b) + (d - c) * 0.5;
//            if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
//                return 1.0 - measure / objects.size();
//            } else {
//                return 1.0 - measure;
//            }
//        }
        return 0.0;
    }

    /* T8 */
    public double degreeOfSummarizerCardinality() {
        return 1.0 - Arrays.stream(summarizers)
                .mapToDouble(summarizer -> summarizer.getFuzzySet()
                        .cardinality(objects) / objects.size())
                .reduce(1.0, (a, b) -> a * b);
    }

    /* T9 */
    public double degreeOfQualifierImprecision() {
        return 1.0 - qualifier.getFuzzySet().degreeOfFuzziness(objects);
    }

    /* T10 */
    public double degreeOfQualifierCardinality() {
        return 1.0 - qualifier.getFuzzySet().cardinality(objects) / objects.size();
    }

    /* T11 */
    public double lengthOfQualifier() {
        return 2.0 * Math.pow(0.5, 1); //there is only one fuzzy set in qualifier for now
    }
}
