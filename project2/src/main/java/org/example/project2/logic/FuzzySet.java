package org.example.project2.logic;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public interface FuzzySet<T> extends Serializable {

    public double degreeOfMembership(T x);

    default double cardinality(List<T> X) {
        return X.stream().mapToDouble(this::degreeOfMembership).sum();
    }

    default List<T> support(List<T> X) {
        return X.stream().filter(x -> degreeOfMembership(x) > 0.0).collect(Collectors.toList());
    }

    default double degreeOfFuzziness(List<T> X) {
        return support(X).size() / (double) X.size();
    }

    default FuzzySet<T> and(FuzzySet<T> set) {
        return (T x)-> Math.min(this.degreeOfMembership(x), set.degreeOfMembership(x));
    }
}
