package org.example.project2.logic.functions;

public class TrapezoidalFunction implements MembershipFunction {

    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double rangeBegin;
    private final double rangeEnd;

    public TrapezoidalFunction(double a, double b, double c, double d,
                               double rangeBegin, double rangeEnd) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.rangeBegin = rangeBegin;
        this.rangeEnd = rangeEnd;
    }

    @Override
    public double degreeOfMembership(double x) {
        if (x < this.a) {
            return 0;
        } else if (x < this.b) {
            return Math.abs((x - this.a) / (this.b - this.a));
        } else if (x < this.c) {
            return 1;
        } else if (x < this.d) {
            return Math.abs((this.d - x) / (this.d - this.c));
        } else {
            return 0;
        }
    }

    @Override
    public double cardinality() {
        return ((d - a) + (c - b)) / 2;
    }

    @Override
    public double support() {
        return this.d - this.a;
    }

    @Override
    public double universeBegin() {
        return a;
    }

    @Override
    public double universeEnd() {
        return d;
    }
}
