package org.example.project2.logic.linguistics;

import java.io.Serializable;

public abstract class Variable<T> implements Serializable {
    private final String name;

    public Variable(final String name) {
        this.name = name;
    }

    public abstract Double extractAttribute(T object);

    public String getName() {
        return name;
    }
}
