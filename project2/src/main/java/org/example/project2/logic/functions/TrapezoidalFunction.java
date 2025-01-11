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
        if (x >= b && x <= c) {
            return 1.0;
        } else if (x <= a || x >= d) {
            return 0.0;
        } else if (x > a && x < b) {
            return (x - a) / (b - a);
        } else {
            return (d - x) / (d - c);
        }
    }

    @Override
    public double area() {
        return ((d - a) + (c - b)) / 2;
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
