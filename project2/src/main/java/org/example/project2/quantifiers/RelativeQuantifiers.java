package org.example.project2.quantifiers;

import org.example.project2.logic.*;

public class RelativeQuantifiers {

    final public Quantifier nearlyNone = new Quantifier("NEARLY_NONE", new TrapezoidalFuzzySet<Double>(x -> x, 0, 0, 0.2, 0.3, 0, 0.3 ), QuantifierType.RELATIVE);
    final public Quantifier around1_4 = new Quantifier("AROUND_1/4", new TriangularFuzzySet<Double>(x -> x, 0.1, 0.3, 0.45,  0.1, 0.45 ), QuantifierType.RELATIVE);
    final public Quantifier aroundHalf = new Quantifier("AROUND_HALF", new GaussianFuzzySet<Double>(x -> x, 0.5, 0.17), QuantifierType.RELATIVE);
    final public Quantifier around3_4 = new Quantifier("AROUND_3/4", new TrapezoidalFuzzySet<Double>(x -> x, 0.55, 0.6, 0.75, 0.8, 0.55, 0.8 ), QuantifierType.RELATIVE);
    final public Quantifier most = new Quantifier("MOST", new TriangularFuzzySet<Double>(x -> x, 0.7, 0.8, 0.95,  0.7, 0.95 ), QuantifierType.RELATIVE);
    final public Quantifier nearlyAll = new Quantifier("NEARLY_ALL", new TrapezoidalFuzzySet<Double>(x -> x, 0.85, 0.9, 1, 1, 0.85, 1 ), QuantifierType.RELATIVE);

    public Quantifier getNearlyNone() {
        return nearlyNone;
    }

    public Quantifier getAround1_4() {
        return around1_4;
    }

    public Quantifier getAroundHalf() {
        return aroundHalf;
    }

    public Quantifier getAround3_4() {
        return around3_4;
    }

    public Quantifier getMost() {
        return most;
    }

    public Quantifier getNearlyAll() {
        return nearlyAll;
    }
}
