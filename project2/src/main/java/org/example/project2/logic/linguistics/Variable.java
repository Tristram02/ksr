package org.example.project2.logic.linguistics;

import org.example.project2.logic.sets.ClassicSet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Variable<T> implements Serializable {
    private final String name;
    private final List<Label> labels;
    private final ClassicSet universeOfDiscourse;

    public Variable(String name, List<Label> labels, ClassicSet universeOfDiscourse) {
        this.name = name;
        this.labels = new ArrayList<>(labels);
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public abstract Double extractAttribute(T object);

    public String getName() {
        return name;
    }
    public List<Label> getLabels() {
        return labels;
    }
    public ClassicSet getUniverseOfDiscourse() {
        return universeOfDiscourse;
    }

    public void addLabel(Label label) {
        labels.add(label);
    }
}
