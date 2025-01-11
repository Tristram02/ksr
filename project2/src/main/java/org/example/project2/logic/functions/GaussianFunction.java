package org.example.project2.logic.functions;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.special.Erf;

import org.example.project2.logic.sets.ClassicSet;

public class GaussianFunction implements MembershipFunction {

    private final double stdev;
    private final double mean;
    private final double start;
    private final double end;

    public GaussianFunction(double mean, double stdev, double start, double end) {
        this.stdev = stdev;
        this.mean = mean;
        this.start = start;
        this.end = end;
    }
    @Override
    public double degreeOfMembership(double x) {
        if (x < start || x > end) {
            return 0;
        }
        double exponent = -1 * Math.pow((x - mean), 2) / Math.pow(2 * stdev, 2);
        return Math.exp(exponent);
    }


    @Override
    public ClassicSet alfacut(ClassicSet universeOfDiscourse, double alfa) {
        double a = Math.sqrt(-2 * Math.pow(this.stdev, 2) * Math.log(alfa));
        double left = Math.max(this.mean - a, universeOfDiscourse.getBegin());
        double right = Math.min(this.mean + a, universeOfDiscourse.getEnd());
        return universeOfDiscourse.getSubset(left, right);
    }

    @Override
    public double area() {
        int numSteps = 10000;
        double stepSize = (end - start) / numSteps;
        double area = 0.0;

        for (int i = 0; i < numSteps; i++) {
            double x = end + i * stepSize;
            area += degreeOfMembership(x) * stepSize;
        }

        return area;
    }

    @Override
    public double universeBegin() {
        return this.mean - 3 * this.stdev;
    }

    @Override
    public double universeEnd() {
        return this.mean + 3 * this.stdev;
    }
}
