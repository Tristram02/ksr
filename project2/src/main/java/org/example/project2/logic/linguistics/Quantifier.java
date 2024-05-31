package org.example.project2.logic.linguistics;

import org.example.project2.logic.sets.FuzzySet;

public class Quantifier {

    private final String name;
    private final FuzzySet fuzzySet;
    private final QuantifierType quantifierType;

    public Quantifier(String name, FuzzySet fuzzySet, QuantifierType quantifierType) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.quantifierType = quantifierType;
    }

    public double compatibilityLevel(double x) {
        return fuzzySet.degreeOfMembership(x);
    }

    public String getName() {
        return name;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public QuantifierType getQuantifierType() {
        return this.quantifierType;
    }

}
