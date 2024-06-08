package org.example.project2.logic.functions;

import org.example.project2.logic.sets.ClassicSet;

public class TrapezoidalFunction implements MembershipFunction {

    private final double a;
    private final double b;
    private final double c;
    private final double d;

    public TrapezoidalFunction(double a, double b, double c, double d,
                               double rangeBegin, double rangeEnd) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double degreeOfMembership(double x) {
        if (x < this.a) {
            return 0;
        } else if (x < this.b) {
            return (1.0 / (this.b - this.a)) * x + ((-this.a) / (this.b - this.a));
        } else if (x < this.c) {
            return 1;
        } else if (x < this.d) {
            return (1.0 / (this.c - this.d)) * x + ((-this.d) / (this.c - this.d));
        } else {
            return 0;
        }
    }

    @Override
    public double area(double beginOfUniverse, double endOfUniverse) {
        double P = ((d - a) + (c - b)) / 2;
        double min = Math.max(beginOfUniverse, this.a);
        double max = Math.min(endOfUniverse, this.d);

        if (min <= a &&
            max >= b) {
            return P;
        }
        double x = min - a;
        double y = max - a;
        double Px = partOfArea(x);
        double Py = P - partOfArea(y);
        return P - Px - Py;
    }

    private double partOfArea(double x) {
        if (x <= this.b - this.a) {
            return 0.5 * (1.0 / (this.b - this.a)) * Math.pow((this.a + x), 2) + (this.a + x) * ((-this.a) / (this.b - this.a)) -
                    0.5 * (1.0 / (this.b - this.a)) * Math.pow(this.a, 2) - this.a * ((-this.a) / (this.b - this.a));
        } else if (x <= this.c - this.a) {
            return 0.5 * (this.b - this.a) + (x - (this.b - this.a));
        } else {
            return 0.5 * (this.b - this.a) + (x - (this.c - this.b)) +
                    0.5 * (1.0 / (this.c - this.d)) * Math.pow((this.a + x), 2) + (this.a + x) * ((-this.d) / (this.c - this.d)) -
                    0.5 * (1.0 / (this.c - this.d)) * Math.pow(this.c, 2) - this.c * ((-this.d) / (this.c - this.d));
        }
    }

    @Override
    public ClassicSet support(ClassicSet universeOfDiscourse) {
        double a = Math.max(this.a, universeOfDiscourse.getBegin());
        double b = Math.min(this.d, universeOfDiscourse.getEnd());
        return universeOfDiscourse.getSubset(a, b);
    }

    @Override
    public ClassicSet alfacut(ClassicSet universeOfDiscourse, double alfa) {
        double left = (alfa - ((-this.a) / (this.b - this.a))) / (1.0 / (this.b - this.a));
        double right = (alfa - ((-this.d) / (this.c - this.d))) / (1.0 / (this.d - this.c));
        double begin = Math.max(left, universeOfDiscourse.getBegin());
        double end = Math.min(right, universeOfDiscourse.getEnd());
        return universeOfDiscourse.getSubset(begin, end);
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
