package org.example.project2.logic;

import java.io.Serializable;

public class Label<T> implements Serializable {
    private Long id;
    private final String name;
    private final FuzzySet<T> fuzzySet;
    private final Variable<T> linguisticVariable;

    public Label(final String name, final FuzzySet<T> fuzzySet,
                 final Variable<T> linguisticVariable) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.linguisticVariable = linguisticVariable;
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

    public FuzzySet<T> getFuzzySet() {
        return fuzzySet;
    }

    public Variable<T> getLinguisticVariable() {
        return linguisticVariable;
    }
}
