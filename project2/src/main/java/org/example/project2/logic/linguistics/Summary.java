package org.example.project2.logic.linguistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class  Summary<T> {
    private Quantifier quantifier;
    private List<Label> qualifiers;
    private List<Label> qualifiers2;
    private final List<Label> summarizers;
    private final List<DataEntry> objects;
    private List<DataEntry> objects2;
    private int form;
    private String subject1;
    private String subject2;
    private List<Double> weights;


    public Summary(Quantifier quantifier, List<Label> qualifiers, List<DataEntry> objects,
                   List<Label> summarizers, List<Double> weights) {
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.objects = objects;
        this.weights = weights;
    }

    public Summary(Quantifier quantifier, List<Label> qualifiers, List<DataEntry> objects,
                   List<DataEntry> objects2, List<Label> summarizers, String subject1, String subject2, boolean subject1Qualifier) {
        this.objects = objects;
        this.objects2 = objects2;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.summarizers = summarizers;
        if (quantifier == null) {
            form = 4;
            this.quantifier = null;
        } else if (qualifiers != null) {
            if (subject1Qualifier) {
                form = 3;
                this.qualifiers = qualifiers;
                this.quantifier = quantifier;
            } else {
                form = 2;
                this.qualifiers2 = qualifiers;
                this.quantifier = quantifier;
            }
        } else {
            form = 1;
            this.quantifier = quantifier;
        }
    }

    /* T1 */
    public double degreeOfTruth() {
        double t1 = 0.0;
        List<Double> a;
        List<Double> b;
        for (DataEntry data : objects) {
            a = new ArrayList<>();
            b = new ArrayList<>();
            for (Label label : summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            if (qualifiers != null) {
                for (Label label : qualifiers) {
                    b.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
                }
            }

            double minB = 1.0;
            if (qualifiers != null) {
                minB = Collections.min(b);
            }
            t1 += Math.min(Collections.min(a), minB);
        }
        if (quantifier.getQuantifierType() == QuantifierType.ABSOLUTE) {
            return quantifier.getFuzzySet().degreeOfMembership(t1);
        } else {
            return quantifier.getFuzzySet().degreeOfMembership(t1 / objects.size());
        }
    }

    /* T2 */
    public double degreeOfImprecision() {
        double t2 = 1;
        for (Label label : summarizers) {
            t2 *= label.getFuzzySet().getMembershipFunction().support() / label.getFuzzySet().getUniverseOfDiscourse().getSize();
        }
        t2 = Math.pow(t2, 1.0 / summarizers.size());
        t2 = 1 - t2;
        return t2;
    }

    /* T3 */
    public double degreeOfCovering() {
        List<Double> a;
        List<Double> b;
        double resA = 0.0;
        double resB = 0.0;
        for (DataEntry data : objects) {
            a = new ArrayList<>();
            b = new ArrayList<>();
            for (Label label : summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            if (qualifiers != null) {
                for (Label label : qualifiers) {
                    b.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
                }
            }

            double minB = 1.0;
            if (qualifiers != null) {
                minB = Collections.min(b);
            }
            double membership = Math.min(Collections.min(a), minB);

            if (membership > 0) {
                resA += 1.0;
            }

            if (minB > 0) {
                resB += 1.0;
            }
        }
        if (resB == 0) {
            return 0.0;
        }
        return resA / resB;
    }

    /* T4 */
    public double degreeOfAppropriateness() {
        List<Integer> moreThanZero = new ArrayList<>();
        for (DataEntry data : objects) {
            for (Label label : summarizers) {
                double a = (label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
                if (a > 0) {
                    moreThanZero.add(1);
                } else {
                    moreThanZero.add(0);
                }
            }
        }
        double t4 = 1.0;
        for (Integer x : moreThanZero) {
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
        for (Label summarizer : summarizers) {
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
        for (Label label : qualifiers) {
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
        for (Label label : qualifiers) {
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
        double q = 0.0;
        double sum = 0.0;
        if (qualifiers == null) {
            List<Double> measures = new ArrayList<>() {{
                add(degreeOfTruth());
                add(degreeOfImprecision());
                add(degreeOfCovering());
                add(degreeOfAppropriateness());
                add(lengthOfSummary());
                add(degreeOfQuantifierImprecision());
                add(degreeOfQuantifierCardinality());
                add(degreeOfSummarizerCardinality());
            }};
            for (int i = 0; i < measures.size(); i++) {
                q += this.weights.get(i) * measures.get(i);
                sum += this.weights.get(i);
            }
        } else {
            List<Double> measures = new ArrayList<>() {{
                add(degreeOfTruth());
                add(degreeOfImprecision());
                add(degreeOfCovering());
                add(degreeOfAppropriateness());
                add(lengthOfSummary());
                add(degreeOfQuantifierImprecision());
                add(degreeOfQuantifierCardinality());
                add(degreeOfSummarizerCardinality());
                add(degreeOfQualifierImprecision());
                add(degreeOfQualifierCardinality());
                add(lengthOfQualifier());
            }};
            for (int i = 0; i < measures.size(); i++) {
                q += this.weights.get(i) * measures.get(i);
                sum += this.weights.get(i);
            }
        }
        return q / sum;
    }

    private double sigmaCount(List<DataEntry> objects) {
        double sum = 0;
        List<Double> a;
        for (DataEntry data: objects) {
            a = new ArrayList<>();
            for (Label label: summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            sum += Collections.min(a);
        }
        return sum;
    }

    private double sigmaCount(List<DataEntry> objects, List<Label> summarizers) {
        double sum = 0;
        List<Double> a;
        for (DataEntry data: objects) {
            a = new ArrayList<>();
            for (Label label: summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            sum += Collections.min(a);
        }
        return sum;
    }

    private double implication(double a, double b) {
        return Math.min(1, 1 - a + b);
    }

    public double degreeOfTruthMultiType1() {
        double sigma1 = sigmaCount(this.objects);
        double sigma2 = sigmaCount(this.objects2);
        return quantifier.getFuzzySet().degreeOfMembership((sigma1 / this.objects.size()) / ((sigma1 / this.objects.size()) + (sigma2 / this.objects2.size())));
    }
    public double degreeOfTruthMultiType2() {
        double sigma1 = sigmaCount(this.objects);
        double sigma2 = sigmaCount(this.objects2, Stream.concat(this.qualifiers2.stream(), this.summarizers.stream()).toList());
        return quantifier.getFuzzySet().degreeOfMembership((sigma1 / this.objects.size()) / ((sigma1 / this.objects.size()) + (sigma2 / this.objects2.size())));
    }
    public double degreeOfTruthMultiType3() {
        double sigma1 = sigmaCount(this.objects, Stream.concat(this.qualifiers.stream(), this.summarizers.stream()).toList());
        double sigma2 = sigmaCount(this.objects2);
        return quantifier.getFuzzySet().degreeOfMembership((sigma1 / this.objects.size()) / ((sigma1 / this.objects.size()) + (sigma2 / this.objects2.size())));
    }
    public double degreeOfTruthMultiType4() {
        double sum = 0;
        List<Double> a;
        for (DataEntry data: this.objects) {
            a = new ArrayList<>();

            for (Label label: this.summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            sum += implication(0, Collections.min(a));
        }
        for (DataEntry data: this.objects2) {
            a = new ArrayList<>();

            for (Label label: this.summarizers) {
                a.add(label.getFuzzySet().degreeOfMembership(data.getValueByName(label.getLinguisticVariableName())));
            }
            sum += implication(Collections.min(a), 0);
        }
        return 1 - (sum / (this.objects.size() + this.objects2.size()));
    }

    public String toStringSingle() {
        String result = "";
        result += STR."\{quantifier.getName().charAt(0)}\{quantifier.getName().substring(1).toLowerCase()} data entries ";
        if (qualifiers != null && qualifiers.size() > 0) {
            result += "having ";
            for (int i = 0; i < qualifiers.size(); i++) {
                result += STR."\{parseNameOfSummarizer(qualifiers.get(i).getName())} \{qualifiers.get(i).getLinguisticVariableName()}";
                if (i < qualifiers.size() - 1) {
                    result += " and ";
                }
            }
        }

        result += " have ";
        for (int i = 0; i < summarizers.size(); i++) {
            result += STR."\{parseNameOfSummarizer(summarizers.get(i).getName())} \{summarizers.get(i).getLinguisticVariableName()}";
            if (i < summarizers.size() - 1) {
                result += " and ";
            }
        }

        return result;
    }

    public String toStringMultiple() {
        String result = "";
        if (this.form != 4) {
            result += quantifier.getName() + " ";
        } else {
            result += "More ";
        }
        result += subject1;

        if (this.form == 3) {
            result += "being/having ";
            for (int i = 0; i < qualifiers.size(); i++) {
                result += qualifiers.get(i).getName() + " " + qualifiers.get(i).getLinguisticVariableName();
                if (i < qualifiers.size() - 1) {
                    result += " and ";
                }
            }
        }

        result += " compare to " + subject2;

        if (this.form == 2) {
            result += " being/having ";
            for (int i = 0; i < qualifiers2.size(); i++) {
                result += qualifiers2.get(i).getName() + " " + qualifiers2.get(i).getLinguisticVariableName();
                if (i < qualifiers2.size() - 1) {
                    result += " and ";
                }
            }
        }

        result += " are/have ";

        for (int i = 0; i < summarizers.size(); i++) {
            result += STR."\{parseNameOfSummarizer(summarizers.get(i).getName().toLowerCase())} \{summarizers.get(i).getLinguisticVariableName().toLowerCase()}";
            if (i < summarizers.size() - 1) {
                result += " and ";
            }
        }
        return result;
    }

    private String parseNameOfSummarizer(String nameOfSummarizer) {
        for (int i = 0; i < nameOfSummarizer.length(); i++) {
            if (Character.isUpperCase(nameOfSummarizer.charAt(i))) {
                return nameOfSummarizer.substring(0, i);
            }
        }
        return nameOfSummarizer;
    }
}
