package org.example.project2.quantifiers;

import org.example.project2.logic.functions.GaussianFunction;
import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.functions.TriangularFunction;
import org.example.project2.logic.linguistics.Quantifier;
import org.example.project2.logic.linguistics.QuantifierType;
import org.example.project2.logic.sets.ClassicSet;
import org.example.project2.logic.sets.FuzzySet;

public class RelativeQuantifiers {

    final public Quantifier nearlyNone = new Quantifier("NEARLY_NONE", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0, 0, 0.2, 0.3, 0, 0.3 )), QuantifierType.RELATIVE);
    final public Quantifier around1_4 = new Quantifier("AROUND_1/4", new FuzzySet(new ClassicSet(0, 1), new TriangularFunction(0.1, 0.3, 0.45,  0.1, 0.45 )), QuantifierType.RELATIVE);
    final public Quantifier aroundHalf = new Quantifier("AROUND_HALF", new FuzzySet(new ClassicSet(0, 1), new GaussianFunction(0.5, 0.17)), QuantifierType.RELATIVE);
    final public Quantifier around3_4 = new Quantifier("AROUND_3/4", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0.55, 0.6, 0.75, 0.8, 0.55, 0.8 )), QuantifierType.RELATIVE);
    final public Quantifier most = new Quantifier("MOST", new FuzzySet(new ClassicSet(0, 1), new TriangularFunction(0.7, 0.8, 0.95,  0.7, 0.95 )), QuantifierType.RELATIVE);
    final public Quantifier nearlyAll = new Quantifier("NEARLY_ALL", new FuzzySet(new ClassicSet(0, 1), new TrapezoidalFunction(0.85, 0.9, 1, 1, 0.85, 1 )), QuantifierType.RELATIVE);

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
