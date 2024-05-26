package org.example.project2.logic.functions;

public interface MembershipFunction {
    public double degreeOfMembership(double x);
    public double cardinality();
    public double support();
    public double universeBegin();
    public double universeEnd();
}
