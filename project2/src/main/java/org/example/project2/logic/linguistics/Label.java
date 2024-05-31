package org.example.project2.logic.linguistics;

import org.example.project2.logic.sets.FuzzySet;

import java.io.Serializable;

public class Label<T> implements Serializable {

    private final String name;
    private final FuzzySet fuzzySet;
    private final String linguisticVariableName;

    public Label(final String name, final FuzzySet fuzzySet,
                 final String linguisticVariableName) {
        this.name = name;
        this.fuzzySet = fuzzySet;
        this.linguisticVariableName = linguisticVariableName;
    }

    public String getName() {
        return name;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public String getLinguisticVariableName() {
        return linguisticVariableName;
    }

}
