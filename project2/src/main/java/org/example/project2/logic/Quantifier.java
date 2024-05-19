package org.example.project2.logic;

public class Quantifier {
    private Long id;
    private final String name;
    private final FuzzySet<Double> fuzzySet;
//    private final QuantifierType quantifierType;

    public Quantifier(String name, FuzzySet<Double> fuzzySet) {
        this.name = name;
        this.fuzzySet = fuzzySet;
    }

    public double compatibilityLevel(double x) {
        return fuzzySet.degreeOfMembership(x);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public FuzzySet<Double> getFuzzySet() {
        return fuzzySet;
    }

}
