package org.example.project2.logic.linguistics;

import org.example.project2.logic.sets.FuzzySet;

public class Quantifier {

    private final String name;
    private final FuzzySet fuzzySet;
    private final QuantifierType quantifierType;

    public Quantifier(String name, FuzzySet fuzzySet, QuantifierType quantifierType) {
        if (!(fuzzySet.isConvex() && fuzzySet.isNormal())) {
            throw new IllegalArgumentException("Cannot create quantifier! Quantifier has to be convex and normal");
        }
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.quantifierType = quantifierType;
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
