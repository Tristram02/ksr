package org.example.project2.logic.sets;

import java.util.List;

public class ClassicSet {
    private List<Double> set;
    private double begin;
    private double end;
    private final boolean discrete;

    public ClassicSet(List<Double> set) {
        this.set = set;
        this.discrete = true;
    }

    public ClassicSet(double begin, double end) {
        this.discrete = false;
        this.begin = begin;
        this.end = end;
    }

    public double getSize() {
        if (this.discrete) {
            return this.set.size();
        } else {
            return end - begin;
        }
    }

    public List<Double> getSet() {
        return this.set;
    }

    public double getBegin() {
        return this.begin;
    }

    public double getEnd() {
        return this.end;
    }

    public boolean isDiscrete() {
        return this.discrete;
    }

    public boolean isEmpty() {
        if (this.discrete) {
            return this.set.isEmpty();
        } else {
            return (begin - end) > 0;
        }
    }
}
