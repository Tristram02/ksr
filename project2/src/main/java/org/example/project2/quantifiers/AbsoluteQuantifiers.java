package org.example.project2.quantifiers;

import org.example.project2.logic.*;

public class AbsoluteQuantifiers {

    Quantifier lessThan1000 = new Quantifier("LESS_THAN_1000", new TrapezoidalFuzzySet<Double>(x -> x, 0, 0, 1000, 2000, 0, 2000 ), QuantifierType.ABSOLUTE);
    Quantifier about2000 = new Quantifier("ABOUT_2000", new TriangularFuzzySet<Double>(x -> x, 1000, 2000, 4500,  1000, 4500 ), QuantifierType.ABSOLUTE);
    Quantifier about5000 = new Quantifier("ABOUT_5000", new TriangularFuzzySet<Double>(x -> x, 3000, 5000, 6000,  3000, 6000 ), QuantifierType.ABSOLUTE);
    Quantifier about6000 = new Quantifier("ABOUT_6000", new GaussianFuzzySet<Double>(x -> x, 6000, 1000), QuantifierType.ABSOLUTE);
    Quantifier over8000 = new Quantifier("OVER_8000", new TrapezoidalFuzzySet<Double>(x -> x, 7000, 8000, 11067, 11067,  7000, 11067 ), QuantifierType.ABSOLUTE);
    Quantifier over10000 = new Quantifier("OVER_10000", new TrapezoidalFuzzySet<Double>(x -> x, 9000, 10000, 11067, 11067, 9000, 11067 ), QuantifierType.ABSOLUTE);

    public Quantifier getLessThan1000() {
        return lessThan1000;
    }

    public Quantifier getAbout2000() {
        return about2000;
    }

    public Quantifier getAbout5000() {
        return about5000;
    }

    public Quantifier getAbout6000() {
        return about6000;
    }

    public Quantifier getOver8000() {
        return over8000;
    }

    public Quantifier getOver10000() {
        return over10000;
    }
}
