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
    private int form = 0;
    private String subject1;
    private String subject2;
    private String subjectOne;
    private List<Double> weights;


    public Summary(Quantifier quantifier, List<Label> qualifiers, List<DataEntry> objects,
                   List<Label> summarizers, List<Double> weights, String subjectOne) {
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.objects = objects;
        this.weights = weights;
        this.subjectOne = subjectOne;
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

    public List<DataEntry> getObjects2() {
        return objects2;
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
        double t2 = 1.0;
        for (Label label: summarizers) {
            List<Double> values = new ArrayList<>();
            for (DataEntry object: objects) {
                values.add(object.getValueByName(label.getLinguisticVariableName()));
            }
            t2 *= label.getFuzzySet().degreeOfFuzziness(values);
        }
        return 1 - Math.pow(t2, 1.0 / summarizers.size());
    }

    /* T3 */
    public double degreeOfCovering() {
        if (qualifiers != null) {
            double a = objects.stream().filter(
                o -> this.summarizers.stream().anyMatch(
                        s -> s.getFuzzySet().degreeOfMembership(o.getValueByName(s.getLinguisticVariableName())) > 0
                ) &&
                this.qualifiers.stream().anyMatch(
                        q -> q.getFuzzySet().degreeOfMembership(o.getValueByName(q.getLinguisticVariableName())) > 0
                )
            ).count();
            if (a == 0) {
                return 0.0;
            }
            double b = this.objects.stream().filter(
                    o -> this.qualifiers.stream().anyMatch(
                            q -> q.getFuzzySet().degreeOfMembership(o.getValueByName(q.getLinguisticVariableName())) > 0
                    )
            ).count();
            return a / b;
        }
        return (double) this.objects.stream().filter(
                o -> this.summarizers.stream().anyMatch(
                        s -> s.getFuzzySet().degreeOfMembership(o.getValueByName(s.getLinguisticVariableName())) > 0)
        ).count() / this.objects.size();
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
        return 1 - this.quantifier.getFuzzySet().degreeOfFuzziness();
    }

    /* T7 */
    public double degreeOfQuantifierCardinality() {
        return 1 - (this.quantifier.getFuzzySet().clm() / quantifier.getFuzzySet().getUniverseOfDiscourse().getSize());
    }

    /* T8 */
    public double degreeOfSummarizerCardinality() {
        double card = 1.0;
        for (Label summarizer : summarizers) {
            card *= summarizer.getFuzzySet().cardinality(objects, summarizer.getLinguisticVariableName()) /
                    objects.size();
        }
        card = Math.pow(card, 1.0 / summarizers.size());
        if (card > 1.0) {
            System.out.println("Coś jest kurwa nie tak");
        }
        return 1 - card;
    }

    /* T9 */
    public double degreeOfQualifierImprecision() {
        if (qualifiers == null) {
            return 0.0;
        }
        double t9 = 1.0;
        for (Label label : qualifiers) {
            List<Double> values = new ArrayList<>();
            for (DataEntry object: objects) {
                values.add(object.getValueByName(label.getLinguisticVariableName()));
            }
            t9 *= label.getFuzzySet().degreeOfFuzziness(values);
        }
        t9 = Math.pow(t9, 1.0 / qualifiers.size());
        return 1.0 - t9;
    }

    /* T10 */
    public double degreeOfQualifierCardinality() {
        if (qualifiers == null) {
            return 0.0;
        }
        double t10 = 1.0;
        for (Label label : qualifiers) {
            t10 *= label.getFuzzySet().cardinality(this.objects, label.getLinguisticVariableName()) / objects.size();
        }
        t10 = Math.pow(t10, 1.0 / qualifiers.size());
        return 1 - t10;
    }

    /* T11 */
    public double lengthOfQualifier() {
        if (qualifiers == null) {
            return 1.0;
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
        return quantifier.getFuzzySet().degreeOfMembership((sigma1 / this.objects.size()) /
                ((sigma1 / this.objects.size()) + (sigma2 / this.objects2.size())));
    }
    public double degreeOfTruthMultiType2() {
        double sigma1 = sigmaCount(this.objects);
        double sigma2 = sigmaCount(this.objects2, Stream.concat(this.qualifiers2.stream(), this.summarizers.stream()).toList());
        return quantifier.getFuzzySet().degreeOfMembership((sigma1 / this.objects.size()) /
                ((sigma1 / this.objects.size()) + (sigma2 / this.objects2.size())));
    }
    public double degreeOfTruthMultiType3() {
        double sigma1 = sigmaCount(this.objects, Stream.concat(this.qualifiers.stream(), this.summarizers.stream()).toList());
        double sigma2 = sigmaCount(this.objects2);
        return quantifier.getFuzzySet().degreeOfMembership((sigma1 / this.objects.size()) /
                ((sigma1 / this.objects.size()) + (sigma2 / this.objects2.size())));
    }
    public double degreeOfTruthMultiType4() {
        double sum = 0;
        int s = Math.max(this.objects.size(), this.objects2.size());
        for(int i = 0; i < s; i++) {
            double a = 1;
            double b = 1;
            if (i < this.objects.size()) {
                List<Double> tmp = new ArrayList<>();
                for (Label label: this.summarizers) {
                    tmp.add(label.getFuzzySet().degreeOfMembership(objects.get(i).getValueByName(label.getLinguisticVariableName())));
                }
                a = Collections.min(tmp);
            }
            if (i < this.objects2.size()) {
                List<Double> tmp = new ArrayList<>();
                for (Label label: this.summarizers) {
                    tmp.add(label.getFuzzySet().degreeOfMembership(objects2.get(i).getValueByName(label.getLinguisticVariableName())));
                }
                b = Collections.min(tmp);
            }
            sum += implication(a, b);
        }
        return 1 - (sum / s);
    }

    public String toStringSingle() {
        String result = "";
        String subject = "";
        if(!subjectOne.equals(" data entries")){
            subject = " countries from " + subjectOne.toLowerCase();
        } else {
            subject = " data entries";
        }
        result += STR."\{quantifier.getName().charAt(0)}\{quantifier.getName().substring(1).toLowerCase()}\{subject}";
        if (qualifiers != null && qualifiers.size() > 0) {
            result += " having ";
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
            result += quantifier.getName().charAt(0) + quantifier.getName().substring(1).toLowerCase() + " ";
        } else {
            result += "More ";
        }
        result += (" countries from " + subject1.toLowerCase()) ;

        if (this.form == 3) {
            result += " having ";
            for (int i = 0; i < qualifiers.size(); i++) {
                result += STR."\{parseNameOfSummarizer(qualifiers.get(i).getName())} \{qualifiers.get(i).getLinguisticVariableName()}";
                if (i < qualifiers.size() - 1) {
                    result += " and ";
                }
            }
        }

        result += " compare to countries from " + subject2.toLowerCase();

        if (this.form == 2) {
            result += " having ";
            for (int i = 0; i < qualifiers2.size(); i++) {
                result += STR."\{parseNameOfSummarizer(qualifiers2.get(i).getName())} \{qualifiers2.get(i).getLinguisticVariableName()}";
                if (i < qualifiers2.size() - 1) {
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

    private String parseNameOfSummarizer(String nameOfSummarizer) {
        for (int i = 0; i < nameOfSummarizer.length(); i++) {
            if (Character.isUpperCase(nameOfSummarizer.charAt(i))) {
                return nameOfSummarizer.substring(0, i);
            }
        }
        return nameOfSummarizer;
    }

    public double getDegreeOfTruthToSort() {
        if(this.form != 0) {
            if(this.form == 1) {
                return degreeOfTruthMultiType1();
            } else if(this.form == 2) {
                return degreeOfTruthMultiType2();
            } else if(this.form == 3) {
                return degreeOfTruthMultiType3();
            } else {
                return degreeOfTruthMultiType4();
            }
        } else {
            return degreeOfTruth();
        }
    }

    public String toString() {
        if (this.form == 0) {
            return toStringSingle();
        } else {
            return toStringMultiple();
        }
    }

    public int getForm() {
        return this.form;
    }
}
