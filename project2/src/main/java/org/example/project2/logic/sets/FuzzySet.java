package org.example.project2.logic.sets;

import org.example.project2.logic.functions.IntersectMembershipFunction;
import org.example.project2.logic.functions.MembershipFunction;
import org.example.project2.logic.linguistics.DataEntry;

import java.util.List;

public class FuzzySet {
    private ClassicSet universeOfDiscourse;
    private MembershipFunction membershipFunction;


    public FuzzySet(ClassicSet set, MembershipFunction membershipFunction) {
        this.universeOfDiscourse = set;
        this.membershipFunction = membershipFunction;
    }

    public ClassicSet getUniverseOfDiscourse() {
        return this.universeOfDiscourse;
    }

    public double degreeOfMembership(double x) {
        return this.universeOfDiscourse.contains(x) ? this.membershipFunction.degreeOfMembership(x) : 0.0;
    }

    public ClassicSet support() {
        if (universeOfDiscourse.isDiscrete()) {
            List<Double> set = universeOfDiscourse.getSet()
                    .stream()
                    .filter(x -> degreeOfMembership(x) > 0)
                    .toList();
            return new ClassicSet(set, membershipFunction.universeBegin(), membershipFunction.universeEnd());
        } else {
            return this.membershipFunction.support(this.universeOfDiscourse);
        }
    }

    public double cardinality(List<DataEntry> objects, String label) {
        double sum = 0.0;
        for (DataEntry object: objects) {
            sum += this.membershipFunction.degreeOfMembership(object.getValueByName(label));
        }
        return sum;
    }

    public double clm() {
        return this.membershipFunction.area(universeOfDiscourse.getBegin(), universeOfDiscourse.getEnd());
    }

    public ClassicSet alfacut(double alfa) {
        return this.membershipFunction.alfacut(universeOfDiscourse, alfa);
    }

    public double height() {
        double height = Double.NEGATIVE_INFINITY;
        if (universeOfDiscourse.isDiscrete()) {
            for (Double x: universeOfDiscourse.getSet()) {
                if (membershipFunction.degreeOfMembership(x) > height) {
                    height = membershipFunction.degreeOfMembership(x);
                }
            }
        } else {
            return 1.0;
        }
        return height;
    }

    public double degreeOfFuzziness() {
        return support().getSize() / universeOfDiscourse.getSize();
    }

    public double degreeOfFuzziness(List<Double> values) {
        double count = values.stream().filter(v -> support().contains(v)).count();
        double all = values.stream().filter(v -> universeOfDiscourse.contains(v)).count();
        return count / all;
    }

    public FuzzySet and(FuzzySet set) {
        MembershipFunction intersect = new IntersectMembershipFunction(this.membershipFunction, set.membershipFunction);
        return new FuzzySet(this.universeOfDiscourse, intersect);
    }

    public boolean isNormal() {
        if (height() != 1) {
            return false;
        }
        return true;
    }

    public boolean isConvex() {
        if (this.universeOfDiscourse.isDiscrete()) {
            for (double x1 : universeOfDiscourse.getSet()) {
                for (double x2 : universeOfDiscourse.getSet()) {
                    if (x1 < x2) {
                        double x = 0.5 * (x1 + x2);
                        if (membershipFunction.degreeOfMembership(x) <
                                Math.min(membershipFunction.degreeOfMembership(x1),
                                         membershipFunction.degreeOfMembership(x2))) {
                            return false;
                        }
                    }
                }
            }
        } else {
            for (double i = universeOfDiscourse.getBegin(); i < universeOfDiscourse.getEnd(); i++) {
                for (double j = universeOfDiscourse.getBegin(); j < universeOfDiscourse.getEnd(); j++) {
                    if (i < j) {
                        double x = 0.5 * (i + j);
                        if (membershipFunction.degreeOfMembership(x) <
                        Math.min(membershipFunction.degreeOfMembership(i), membershipFunction.degreeOfMembership(j))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
