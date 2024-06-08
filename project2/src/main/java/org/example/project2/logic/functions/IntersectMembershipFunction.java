package org.example.project2.logic.functions;

import org.example.project2.logic.sets.ClassicSet;

public class IntersectMembershipFunction implements MembershipFunction {
    private final MembershipFunction function1;
    private final MembershipFunction function2;

    public IntersectMembershipFunction(MembershipFunction function1, MembershipFunction function2) {
        this.function1 = function1;
        this.function2 = function2;
    }

    @Override
    public double degreeOfMembership(double x) {
        return Math.min(function1.degreeOfMembership(x), function2.degreeOfMembership(x));
    }

    @Override
    public ClassicSet support(ClassicSet universeOfDiscourse) {
        double begin = Math.max(function1.universeBegin(), function2.universeBegin());
        double end = Math.min(function1.universeEnd(), function2.universeEnd());
        begin = Math.max(begin, universeOfDiscourse.getBegin());
        end = Math.min(end, universeOfDiscourse.getEnd());
        return universeOfDiscourse.getSubset(begin, end);
    }

    @Override
    public double area(double beginOfUniverse, double endOfUniverse) {
        double step = 0.01;
        double area = 0;
        for (double x = beginOfUniverse; x <= endOfUniverse; x += step) {
            double minDegree = Math.min(function1.degreeOfMembership(x), function2.degreeOfMembership(x));
            area += minDegree * step;
        }
        return area;
    }

    @Override
    public ClassicSet alfacut(ClassicSet universeOfDiscourse, double alfa) {
        ClassicSet alfacut1 = function1.alfacut(universeOfDiscourse, alfa);
        ClassicSet alfacut2 = function2.alfacut(universeOfDiscourse, alfa);
        double begin = Math.max(alfacut1.getBegin(), alfacut2.getBegin());
        double end = Math.min(alfacut1.getEnd(), alfacut2.getEnd());
        return universeOfDiscourse.getSubset(begin, end);
    }
    @Override
    public double universeBegin() {
        return Math.max(function1.universeBegin(), function2.universeBegin());
    }

    @Override
    public double universeEnd() {
        return Math.min(function1.universeEnd(), function2.universeEnd());
    }
}
