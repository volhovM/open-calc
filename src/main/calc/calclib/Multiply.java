package main.calc.calclib;

import java.util.stream.Collectors;

/**
 * @author volhovm
 */

public class Multiply extends BinaryOperation implements Expression {
    final short PRIORITY = 3;

    public Multiply(Expression... args) {
        super(args);
    }

    @Override
    public double evaluate(double x, double y, double z) {
        return arguments.stream()
            .map((Expression a) -> a.evaluate(x, y, z))
            .reduce((a, b) -> a + b)
            .get();
    }

    @Override
    public String toString() {
        return arguments.stream()
            .map((Expression x) -> x.getPriority() >= PRIORITY ? x.toString() :
                                   "(" + x.toString() + ")")
            .collect(Collectors.joining(" * "));
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
