package org.example.project2.logic.linguistics;

import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.sets.FuzzySet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Summary<T> {
    private final Quantifier quantifier;
    private final Label<T>[] qualifiers;
    private final Label<T>[] summarizers;
    private final FuzzySet fuzzySetOfCompoundSummarizer;
    private final List<DataEntry> objects;

    public Summary(Quantifier quantifier, Label<T>[] qualifiers, List<DataEntry> objects,
                             Label<T>... summarizers) {
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
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
        double r = 0;
        double r2 = 0;
        double m = 0;
        double m2 = 0;

        if (qualifiers == null) { // summary without qualifier
            for (DataEntry obj : objects) {
                if (summarizers.length == 1) {
                    r += summarizers[0].getFuzzySet().degreeOfMembership(obj.getValueByName(summarizers[0].getLinguisticVariable().getName()));
                } else if (summarizers.length > 1) {
                    double min = 1.0;
                    for (Label<T> summarizer : summarizers) {
                        double membership = summarizer.getFuzzySet().degreeOfMembership(obj.getValueByName(summarizers[0].getLinguisticVariable().getName()));
                        if (membership < min) {
                            min = membership;
                        }
                    }
                    r += min;
                }
            }
            m = objects.size();
        } else {
            for (DataEntry obj : objects) {
                if (summarizers.length == 1) {
                    r += Math.min(summarizers[0].getFuzzySet().degreeOfMembership(obj.getValueByName(summarizers[0].getLinguisticVariable().getName())), qualifiers[0].getFuzzySet().degreeOfMembership(obj.getValueByName(summarizers[0].getLinguisticVariable().getName())));
                } else if (summarizers.length > 1) {
                    double a;
                    double min = 1.0;
                    for (Label<T> summarizer : summarizers) {
                        double membership = summarizer.getFuzzySet().degreeOfMembership(obj.getValueByName(summarizers[0].getLinguisticVariable().getName()));
                        if (membership < min) {
                            min = membership;
                        }
                    }
                    a = min;
                    r += Math.min(a, qualifiers[0].getFuzzySet().degreeOfMembership(obj.getValueByName(summarizers[0].getLinguisticVariable().getName())));
                }
                m += qualifiers[0].getFuzzySet().degreeOfMembership(obj.getValueByName(summarizers[0].getLinguisticVariable().getName()));
            }
        }

        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE && qualifiers == null) {
            return quantifier.getFuzzySet().getMembershipFunction().degreeOfMembership(r);
        } else if (!(quantifier.getQuantifierType() == QuantifierType.ABSOLUTE)) {
            return quantifier.getFuzzySet().getMembershipFunction().degreeOfMembership(r / m);
        } else return 0.0;
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
            if (qualifiers != null) {
                membershipQ = qualifiers[0].getFuzzySet().degreeOfMembership(dataEntry.getValueByName(qualifiers[0].getName()));
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
        if (qualifiers != null) {
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
        if (qualifiers == null) {
            return 0.0;
        }
        return 1.0 - qualifiers[0].getFuzzySet().degreeOfFuzziness(values);
    }

    /* T10 */
    public double degreeOfQualifierCardinality() {
        if (qualifiers == null) {
            return 0.0;
        }
        return 1.0 - qualifiers[0].getFuzzySet().cardinality() / qualifiers[0].getFuzzySet().getUniverseOfDiscourse().getSize();
    }

    /* T11 */
    public double lengthOfQualifier() {
        return 2.0 * Math.pow(0.5, 1); //there is only one fuzzy set in qualifier for now
    }

    public double quality() {
        List<Double> weights = new ArrayList<>();
        double q = 0.0;
        double sum = 0.0;
        if (qualifiers == null) {
            List<Double> measures = new ArrayList<>() {{
                add(degreeOfTruth()); add(degreeOfImprecision()); add(degreeOfCovering()); add(degreeOfAppropriateness());
                add(lengthOfSummary()); add(degreeOfQuantifierImprecision()); add(degreeOfQuantifierCardinality());
                add(degreeOfSummarizerCardinality());
            }};
            weights.add(0.7);
            for (int i = 0; i < measures.size() - 1; i++) {
                weights.add(0.03);
            }
            for (int i = 0; i < measures.size(); i++) {
                q += weights.get(i) * measures.get(i);
                sum += weights.get(i);
            }
        } else {
            List<Double> measures = new ArrayList<>() {{
                add(degreeOfTruth()); add(degreeOfImprecision()); add(degreeOfCovering()); add(degreeOfAppropriateness());
                add(lengthOfSummary()); add(degreeOfQuantifierImprecision()); add(degreeOfQuantifierCardinality());
                add(degreeOfSummarizerCardinality()); add(degreeOfQualifierImprecision()); add(degreeOfQualifierCardinality());
                add(lengthOfQualifier());
            }};
            weights.add(0.7);
            for (int i = 0; i < measures.size() - 1; i++) {
                weights.add(0.03);
            }
            for (int i = 0; i < measures.size(); i++) {
                q += weights.get(i) * measures.get(i);
                sum += weights.get(i);
            }
        }
        return q / sum;
    }
}
