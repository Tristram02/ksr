package org.example.project2.logic.linguistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Variable<T> implements Serializable {
    private final String name;
    private final List<Label> labels;

    public Variable(String name, List<Label> labels) {
        this.name = name;
        this.labels = new ArrayList<>(labels);
    }

    public abstract Double extractAttribute(T object);

    public String getName() {
        return name;
    }
    public List<Label> getLabels() {
        return labels;
    }

    public void addLabel(Label label) {
        labels.add(label);
    }
}
