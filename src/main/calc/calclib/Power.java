package main.calc.calclib;


import java.util.stream.Collectors;

/**
 * @author volhovm
 */
public class Power extends BinaryOperation {
    final short PRIORITY = 5;

    public Power(Expression... args) {
        super(args);
    }

    @Override
    public double evaluate(double x, double y, double z) {
        return arguments.stream()
            .map((Expression a) -> a.evaluate(x, y, z))
            .reduce(Math::pow)
            .get();
    }

    @Override
    public String toString() {
        return arguments.stream()
            .map((Expression x) -> x.getPriority() >= PRIORITY ? x.toString() :
                                   "(" + x.toString() + ")")
            .collect(Collectors.joining(" ^ "));
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
