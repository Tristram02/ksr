package org.example.project2.logic.functions;

import org.example.project2.logic.sets.ClassicSet;

public interface MembershipFunction {
    public double degreeOfMembership(double x);
    public double area(double beginOfUniverse, double endOfUniverse);
    public ClassicSet support(ClassicSet universeOfDiscourse);
    public ClassicSet alfacut(ClassicSet universeOfDiscourse, double alfa);
    public double universeBegin();
    public double universeEnd();
}
