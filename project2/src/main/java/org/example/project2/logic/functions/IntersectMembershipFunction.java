package org.example.project2.logic.functions;

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
    public double cardinality() {
        return 0;//TODO
    }

    @Override
    public double support() {
        return 0;//TODO
    }

    @Override
    public double universeBegin() {
        return 0;//TODO
    }

    @Override
    public double universeEnd() {
        return 0;//TODO
    }
}
