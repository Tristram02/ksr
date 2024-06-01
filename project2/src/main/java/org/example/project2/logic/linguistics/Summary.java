package org.example.project2.logic.linguistics;

import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.sets.FuzzySet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Summary<T> {
    private final Quantifier quantifier;
    private final List<Label> qualifiers;
    private final List<Label> summarizers;
    private final List<DataEntry> objects;

    public Summary(Quantifier quantifier, List<Label> qualifiers, List<DataEntry> objects,
                   List<Label> summarizers) {
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.objects = objects;
    }

    /* T1 */
    public double degreeOfTruth() {
        double t1 = 0.0;
        List<Double> a = new ArrayList<>();
        List<Double> b = new ArrayList<>();
        double sumB = 0.0;
        for (DataEntry data: objects) {
            for (Label label: summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            if (qualifiers != null) {
                for (Label label: qualifiers) {
                    a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
                }
            }

            double minB = 1.0;
            if (qualifiers != null) {
                minB = Collections.min(b);
            }
            t1 += Math.min(Collections.min(a), minB);
            sumB += minB;
        }
        if (qualifiers != null) {
            return quantifier.getFuzzySet().degreeOfMembership(t1 / sumB);
        } else {
            return quantifier.getFuzzySet().degreeOfMembership(t1);
        }
    }

    /* T2 */
    public double degreeOfImprecision() {
        double t2 = 1;
        for (Label label: summarizers) {
            t2 *= label.getFuzzySet().getMembershipFunction().support() / label.getFuzzySet().getUniverseOfDiscourse().getSize();
        }
        t2 = Math.pow(t2, 1.0 / summarizers.size());
        t2 = 1 - t2;
        return t2;
    }

    /* T3 */
    public double degreeOfCovering() {
        double t3 = 0.0;
        List<Double> a = new ArrayList<>();
        List<Double> b = new ArrayList<>();
        double resA = 0.0;
        double resB = 0.0;
        for (DataEntry data: objects) {
            for (Label label: summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            if (qualifiers != null) {
                for (Label label: qualifiers) {
                    b.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
                }
            }

            double minB = 1.0;
            if (qualifiers != null) {
                minB = Collections.min(b);
            }
            t3 += Math.min(Collections.min(a), minB);

            if (t3 > 0) {
                resA += 1.0;
            }

            if (minB > 0) {
                resB += 1.0;
            }
        }
        return resA / resB;
    }

    /* T4 */
    public double degreeOfAppropriateness() {
        List<Integer> moreThanZero = new ArrayList<>();
        for (DataEntry data: objects) {
            for (Label label: summarizers) {
                double a = (label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
                if (a > 0) {
                    moreThanZero.add(1);
                } else {
                    moreThanZero.add(0);
                }
            }
        }
        double t4 = 1.0;
        for (Integer x: moreThanZero) {
            t4 *= (x * 1.0) / objects.size();
        }
        t4 -= degreeOfCovering();
        return Math.abs(t4);
    }

    /* T5 */
    public double lengthOfSummary() {
        return 2.0 * Math.pow(0.5, summarizers.size());
    }

    /* T6 */
    public double degreeOfQuantifierImprecision() {
        double supp = quantifier.getFuzzySet().getMembershipFunction().support();
        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
            supp /= objects.size();
        }
        return 1 - supp;
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
        card = Math.pow(card, 1.0 / summarizers.size());
        return 1 - card;
    }

    /* T9 */
    public double degreeOfQualifierImprecision() {
        if (qualifiers == null) {
            return 1.0;
        }
        double t9 = 1.0;
        for (Label label: qualifiers) {
            t9 *= label.getFuzzySet().getMembershipFunction().support() / label.getFuzzySet().getUniverseOfDiscourse().getSize();
        }
        t9 = Math.pow(t9, 1.0 / qualifiers.size());
        return 1.0 - t9;
    }

    /* T10 */
    public double degreeOfQualifierCardinality() {
        if (qualifiers == null) {
            return 1.0;
        }
        double t10 = 1.0;
        for (Label label: qualifiers) {
            t10 *= label.getFuzzySet().cardinality() / label.getFuzzySet().getUniverseOfDiscourse().getSize();
        }
        t10 = Math.pow(t10, 1.0 / qualifiers.size());
        return 1 - t10;
    }

    /* T11 */
    public double lengthOfQualifier() {
        if (qualifiers == null) {
            return 0.0;
        }
        return 2.0 * Math.pow(0.5, qualifiers.size());
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
                weights.add(0.042);
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

    public String toString() {
        String result = "";
        result += quantifier.getName() + " data entries ";
        if (qualifiers != null && qualifiers.size() > 0) {
            result += "being/having ";
            for (int i = 0; i < qualifiers.size(); i++) {
                result += qualifiers.get(i).getName() + " " + qualifiers.get(i).getLinguisticVariableName();
                if (i < qualifiers.size() - 1) {
                    result += " and ";
                }
            }
        }

        result += " are/have ";
        for (int i = 0; i < summarizers.size(); i++) {
            result += summarizers.get(i).getName() + " " + summarizers.get(i).getLinguisticVariableName();
            if (i < summarizers.size() - 1) {
                result += " and ";
            }
        }

        return result;
    }
}
