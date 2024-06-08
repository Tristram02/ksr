package org.example.project2.logic.sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClassicSet {
    private List<Double> set;
    private double begin;
    private double end;
    private final boolean discrete;

    public ClassicSet(List<Double> set, double begin, double end) {
        this.set = set;
        this.begin = begin;
        this.end = end;
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
            return isEmpty() ? 0.0 : end - begin;
        }
    }

    public boolean contains(Double element) {
        if (this.discrete && !isEmpty()) {
            return this.set.contains(element);
        } else return !isEmpty() &&
                element >= this.begin &&
                element <= this.end;
    }

    public ClassicSet getSubset(Double begin, Double end) {
        if (isEmpty()) {
            return new ClassicSet(0, 0);
        }
        if (this.discrete) {
            List<Double> subset = new ArrayList<>();
            for (Double element: this.set) {
                if (element >= begin && element <= end) {
                    subset.add(element);
                }
            }
            return new ClassicSet(subset, begin, end);
        } else {
            double newBegin = Math.max(begin, this.begin);
            double newEnd = Math.min(end, this.end);
            return (newBegin <= newEnd) ? new ClassicSet(newBegin, newEnd) : new ClassicSet(0, 0);
        }
    }

    public List<Double> getSet() {
        return this.set;
    }

    public double getBegin() {
        if (this.discrete) {
            return Collections.min(set);
        }
        return this.begin;
    }

    public double getEnd() {
        if (this.discrete) {
            return Collections.max(set);
        }
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
