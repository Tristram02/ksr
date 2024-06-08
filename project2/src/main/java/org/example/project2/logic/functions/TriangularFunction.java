package org.example.project2.logic.functions;

import org.example.project2.logic.sets.ClassicSet;

public class TriangularFunction extends TrapezoidalFunction {

    public TriangularFunction(double a, double b, double c,
                              double universeBegin, double universeEnd) {
        super(a, b, b, c, universeBegin, universeEnd);
    }

    @Override
    public double degreeOfMembership(double x) {
        return super.degreeOfMembership(x);
    }

    @Override
    public ClassicSet support(ClassicSet universeOfDiscourse) {
        return super.support(universeOfDiscourse);
    }

    @Override
    public ClassicSet alfacut(ClassicSet universeOfDiscourse, double alfa) {
        return super.alfacut(universeOfDiscourse, alfa);
    }

    @Override
    public double area(double beginOfUniverse, double endOfUniverse) {
        return super.area(beginOfUniverse, endOfUniverse);
    }

    @Override
    public double universeBegin() {
        return super.universeBegin();
    }

    @Override
    public double universeEnd() {
        return super.universeEnd();
    }
}
