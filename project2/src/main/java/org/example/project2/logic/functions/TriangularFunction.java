package org.example.project2.logic.functions;

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
    public double cardinality() {
        return super.cardinality();
    }

    @Override
    public double support() {
        return super.support();
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
