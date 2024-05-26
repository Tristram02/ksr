package org.example.project2.logic.functions;

public class GaussianFunction implements MembershipFunction {

    private final double stdev;
    private final double mean;

    public GaussianFunction(double mean, double stdev) {
        this.stdev = stdev;
        this.mean = mean;
    }
    @Override
    public double degreeOfMembership(double x) {
        return Math.exp(-Math.pow((x - mean), 2) / (2 * Math.pow(stdev, 2)));
    }

    @Override
    public double cardinality() {
        return Math.sqrt(2 * Math.PI) * stdev;
    }

    @Override
    public double support() {
        return this.stdev * 4;
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
