package org.example.project2.quantifiers;

import org.example.project2.logic.functions.GaussianFunction;
import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.functions.TriangularFunction;
import org.example.project2.logic.linguistics.Quantifier;
import org.example.project2.logic.linguistics.QuantifierType;
import org.example.project2.logic.sets.ClassicSet;
import org.example.project2.logic.sets.FuzzySet;

public class AbsoluteQuantifiers {

    Quantifier lessThan1000 = new Quantifier("LESS_THAN_1000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(0, 0, 1000, 2000, 0, 2000 )), QuantifierType.ABSOLUTE);
    Quantifier about2000 = new Quantifier("ABOUT_2000", new FuzzySet(new ClassicSet(0, 11067), new TriangularFunction(1000, 2000, 4500,  1000, 4500 )), QuantifierType.ABSOLUTE);
    Quantifier about5000 = new Quantifier("ABOUT_5000", new FuzzySet(new ClassicSet(0, 11067), new TriangularFunction(3000, 5000, 6000,  3000, 6000 )), QuantifierType.ABSOLUTE);
    Quantifier about6000 = new Quantifier("ABOUT_6000", new FuzzySet(new ClassicSet(0, 11067), new GaussianFunction(6000, 1000)), QuantifierType.ABSOLUTE);
    Quantifier over8000 = new Quantifier("OVER_8000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(7000, 8000, 11067, 11067,  7000, 11067 )), QuantifierType.ABSOLUTE);
    Quantifier over10000 = new Quantifier("OVER_10000", new FuzzySet(new ClassicSet(0, 11067), new TrapezoidalFunction(9000, 10000, 11067, 11067, 9000, 11067 )), QuantifierType.ABSOLUTE);

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
