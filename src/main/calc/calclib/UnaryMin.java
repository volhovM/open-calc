package main.calc.calclib;

/**
 * @author volhovm
 */

public class UnaryMin extends UnaryOperation implements Expression {
    final short PRIORITY = 4;

    public UnaryMin(Expression a) {
        super(a);
    }

    @Override
    public double evaluate(double x, double y, double z) {
        return -a.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "- " + (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
