package org.example.project2.logic.linguistics;

import java.io.Serializable;
import java.util.List;

public abstract class Variable<T> implements Serializable {
    private final String name;
    private List<Label> labels;

    public Variable(final String name) {
        this.name = name;
    }

    public abstract Double extractAttribute(T object);

    public String getName() {
        return name;
    }
}
