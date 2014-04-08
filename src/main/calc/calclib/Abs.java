package main.calc.calclib;

/**
 * @author volhovm
 */

public class Abs extends UnaryOperation implements Expression {
    final short PRIORITY = 4;

    public Abs(Expression a) {
        super(a);
    }

    @Override
    public double evaluate(double x, double y, double z) {
        return Math.abs(a.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "abs(" + a.toString() + ")";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
