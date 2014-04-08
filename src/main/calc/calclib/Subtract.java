package main.calc.calclib;

import java.util.stream.Collectors;

/**
 * @author volhovm
 */

@SuppressWarnings("UnusedDeclaration")
@Deprecated
public class Subtract extends BinaryOperation implements Expression {
    final static short PRIORITY = 2;

    public Subtract(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    public double evaluate(double x, double y, double z) {
        return arguments.stream()
            .map((Expression a) -> a.evaluate(x, y, z))
            .reduce((a, b) -> a - b)
            .get();
    }

    @Override
    public String toString() {
        return arguments.stream()
            .map((Expression x) -> x.getPriority() >= PRIORITY ? x.toString() :
                                   "(" + x.toString() + ")")
            .collect(Collectors.joining(" - "));
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
