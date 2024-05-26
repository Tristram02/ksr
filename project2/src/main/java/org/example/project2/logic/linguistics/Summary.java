package org.example.project2.logic.linguistics;

import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.sets.FuzzySet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Summary<T> {
    private final Quantifier quantifier;
    private final Label<T> qualifier;
    private final Label<T>[] summarizers;
    private final FuzzySet fuzzySetOfCompoundSummarizer;
    private final List<DataEntry> objects;

    public Summary(Quantifier quantifier, Label<T> qualifier, List<DataEntry> objects,
                             Label<T>... summarizers) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizers = summarizers;
        FuzzySet tmpFuzzySet = summarizers[0].getFuzzySet();
        for (int i = 1; i < summarizers.length; i++) {
            tmpFuzzySet = tmpFuzzySet.and(summarizers[i].getFuzzySet());
        }
        this.fuzzySetOfCompoundSummarizer = tmpFuzzySet;
        this.objects = objects;
    }

    /* T1 */
    public double degreeOfTruth() {
        if (qualifier == null) {
            return 0.0;
        }
        /* If quantifier is absolute, it has to be the first form of linguistic summary */
        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
            return quantifier.compatibilityLevel(fuzzySetOfCompoundSummarizer.cardinality());
        }
        /* If quantifier is relative, calculate as for the first, or the second form */
        else {
            return quantifier
                    .compatibilityLevel(fuzzySetOfCompoundSummarizer
                            .and(qualifier.getFuzzySet())
                            .cardinality() / qualifier
                            .getFuzzySet().cardinality());
        }
    }

    /* T2 */
    public double degreeOfImprecision() {
        double t2 = 1;
        List<Double> values = new ArrayList<>();
        for (Label summarizer: this.summarizers) {
            for (DataEntry dataEntry: objects) {
                values.add(dataEntry.getValueByName(summarizer.getLinguisticVariable().getName()));
            }
            t2 *= summarizer.getFuzzySet().degreeOfFuzziness(values);
            values.clear();
        }
        return 1 - Math.pow(t2, 1.0 / this.summarizers.length);
    }

    /* T3 */
    public double degreeOfCovering() {
        int t = 0;
        int h = 0;
        double membershipQ = 0.0;
        List<Double> listQ = new ArrayList<>();
        for (DataEntry dataEntry : objects) {
            if (qualifier != null) {
                membershipQ = qualifier.getFuzzySet().degreeOfMembership(dataEntry.getValueByName(qualifier.getName()));
            }
            if (membershipQ > 0.0) {
                h++;
                for (Label summarizer : summarizers) {
                    listQ.add(summarizer.getFuzzySet().degreeOfMembership(dataEntry.getValueByName(summarizer.getName())));
                }
                membershipQ = Collections.min(listQ);
                listQ.removeAll(listQ);
                if (membershipQ > 0.0) {
                    t++;
                }
            }
        }
        if (qualifier != null) {
            if (h == 0 || t == 0) {
                return 0.0;
            } else {
                return (double) t / h;
            }
        } else {
            for (DataEntry dataEntry : objects) {
                for (Label summarizer : summarizers) {
                    listQ.add(summarizer.getFuzzySet().degreeOfMembership(dataEntry.getValueByName(summarizer.getName())));
                }
                membershipQ = Collections.min(listQ);
                listQ.removeAll(listQ);
                if (membershipQ > 0.0) {
                    t++;
                }
            }
            return (double) t / objects.size();
        }
    }

    /* T4 */
    public double degreeOfAppropriateness() {
        double p = 1.0;
        for (Label summarizer : summarizers) {
            double r = 0.0;
            for (DataEntry dataEntry : objects) {
                if (summarizer.getFuzzySet().degreeOfMembership(dataEntry.getValueByName(summarizer.getName())) > 0.0) {
                    r++;
                }
            }
            p *= r / objects.size();
        }
        return Math.abs(p - this.degreeOfCovering());
    }

    /* T5 */
    public double lengthOfSummary() {
        return 2.0 * Math.pow(0.5, summarizers.length);
    }

    /* T6 */
    public double degreeOfQuantifierImprecision() {
        if (quantifier.getFuzzySet().getMembershipFunction() instanceof TrapezoidalFunction) {
            FuzzySet quantifierFuzzySet =
                    (FuzzySet) quantifier.getFuzzySet();
            double continuousSupportLength = quantifierFuzzySet.getUniverseOfDiscourse().getEnd() - quantifierFuzzySet.getUniverseOfDiscourse().getBegin();
            if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
                return 1.0 - continuousSupportLength / objects.size();
            } else {
                return 1.0 - continuousSupportLength;
            }
        }
        return 0.0;
    }

    /* T7 */
    public double degreeOfQuantifierCardinality() {
        double card = quantifier.getFuzzySet().cardinality();
        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
            card /= objects.size();
        }
        return 1 - card;
    }

    /* T8 */
    public double degreeOfSummarizerCardinality() {
        double card = 1;
        for (Label summarizer: summarizers) {
            card *= summarizer.getFuzzySet().cardinality() / summarizer.getFuzzySet().getUniverseOfDiscourse().getSize();
        }
        card = Math.pow(card, 1.0 / summarizers.length);
        return 1 - card;
    }

    /* T9 */
    public double degreeOfQualifierImprecision() {
        List<Double> values = new ArrayList<>();
        for (Label summarizer: this.summarizers) {
            for (DataEntry dataEntry: objects) {
                values.add(dataEntry.getValueByName(summarizer.getLinguisticVariable().getName()));
            }
        }
        if (qualifier == null) {
            return 0.0;
        }
        return 1.0 - qualifier.getFuzzySet().degreeOfFuzziness(values);
    }

    /* T10 */
    public double degreeOfQualifierCardinality() {
        if (qualifier == null) {
            return 0.0;
        }
        return 1.0 - qualifier.getFuzzySet().cardinality() / qualifier.getFuzzySet().getUniverseOfDiscourse().getSize();
    }

    /* T11 */
    public double lengthOfQualifier() {
        return 2.0 * Math.pow(0.5, 1); //there is only one fuzzy set in qualifier for now
    }
}
