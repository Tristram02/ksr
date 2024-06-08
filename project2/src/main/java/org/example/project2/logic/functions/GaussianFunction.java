package org.example.project2.logic.functions;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.special.Erf;

import org.example.project2.logic.sets.ClassicSet;

public class GaussianFunction implements MembershipFunction {

    private final double stdev;
    private final double mean;

    public GaussianFunction(double mean, double stdev) {
        this.stdev = stdev;
        this.mean = mean;
    }
    @Override
    public double degreeOfMembership(double x) {
        return Math.exp(-0.5 * Math.pow((x - mean) / stdev, 2));
    }

    @Override
    public ClassicSet support(ClassicSet universeOfDiscourse) {
        return universeOfDiscourse.getSubset(
                universeOfDiscourse.getBegin(),
                universeOfDiscourse.getEnd()
        );
    }

    @Override
    public ClassicSet alfacut(ClassicSet universeOfDiscourse, double alfa) {
        double a = Math.sqrt(-2 * Math.pow(this.stdev, 2) * Math.log(alfa));
        double left = Math.max(this.mean - a, universeOfDiscourse.getBegin());
        double right = Math.min(this.mean + a, universeOfDiscourse.getEnd());
        return universeOfDiscourse.getSubset(left, right);
    }

    @Override
    public double area(double beginOfUniverse, double endOfUniverse) {
        UnivariateFunction gaussianFunction = x -> (1 / (stdev * Math.sqrt(2 * Math.PI))) * Math.exp(-0.5 * Math.pow((x - mean) / stdev, 2));
        BaseAbstractUnivariateIntegrator integrator = new SimpsonIntegrator();
        return integrator.integrate(Integer.MAX_VALUE, gaussianFunction, beginOfUniverse, endOfUniverse);
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
