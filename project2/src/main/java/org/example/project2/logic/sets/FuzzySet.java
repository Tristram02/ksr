package org.example.project2.logic.sets;

import org.example.project2.logic.functions.IntersectMembershipFunction;
import org.example.project2.logic.functions.MembershipFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FuzzySet {
    private ClassicSet universeOfDiscourse;
    private MembershipFunction membershipFunction;


    public FuzzySet(ClassicSet set, MembershipFunction membershipFunction) {
        this.universeOfDiscourse = set;
        this.membershipFunction = membershipFunction;
    }

    public double degreeOfMembership(double x) {
        return this.membershipFunction.degreeOfMembership(x);
    }

    public ClassicSet getUniverseOfDiscourse() {
        return this.universeOfDiscourse;
    }

    public MembershipFunction getMembershipFunction() {
        return this.membershipFunction;
    }

    public ClassicSet support() {
        if (universeOfDiscourse.isDiscrete()) {
            List<Double> set = universeOfDiscourse.getSet()
                    .stream()
                    .filter(x -> degreeOfMembership(x) > 0)
                    .toList();
            return new ClassicSet(set);
        } else {
            return new ClassicSet(membershipFunction.universeBegin(), membershipFunction.universeEnd());
        }
    }

    public ClassicSet support(List<Double> values) {
        List<Double> set = values.stream()
                .filter(x -> degreeOfMembership(x) > 0)
                .toList();
        return new ClassicSet(set);
    }

    public double cardinality() {
        if (universeOfDiscourse.isDiscrete()) {
            return universeOfDiscourse.getSet()
                    .stream()
                    .mapToDouble(this::degreeOfMembership)
                    .sum();
        } else {
            return membershipFunction.cardinality();
        }
    }

    public ClassicSet alphacut(double alfa) {
        if (universeOfDiscourse.isDiscrete()) {
            List<Double> set = new ArrayList<>();
            for (Double x: universeOfDiscourse.getSet()) {
                if (membershipFunction.degreeOfMembership(x) > alfa) {
                    set.add(x);
                }
                return new ClassicSet(set);
            }
        }
        return null;
    }

    public double height() {
        double height = Double.NEGATIVE_INFINITY;
        if (universeOfDiscourse.isDiscrete()) {
            for (Double x: universeOfDiscourse.getSet()) {
                if (membershipFunction.degreeOfMembership(x) > height) {
                    height = membershipFunction.degreeOfMembership(x);
                }
            }
        }
        return height;
    }

    public double degreeOfFuzziness() {
        return support().getSize() / universeOfDiscourse.getSize();
    }

    public double degreeOfFuzziness(List<Double> values) {
        return support(values).getSize() / universeOfDiscourse.getSize();
    }

    public FuzzySet and(FuzzySet set) {
        MembershipFunction intersect = new IntersectMembershipFunction(this.membershipFunction, set.membershipFunction);
        return new FuzzySet(this.universeOfDiscourse, intersect);
    }

    public boolean isNormal() {
        if (height() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isConvex() {
        for (double x1 : universeOfDiscourse.getSet()) {
            for (double x2 : universeOfDiscourse.getSet()) {
                if (x1 < x2) {
                    double x = 0.5 * (x1 + x2);
                    if (membershipFunction.degreeOfMembership(x) < Math.min(membershipFunction.degreeOfMembership(x1), membershipFunction.degreeOfMembership(x2))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
