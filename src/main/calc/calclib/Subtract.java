package main.calc.calclib;

/**
 * @author volhovm
 */

public class Subtract extends BinaryOperations implements Expression3 {
    final static short PRIORITY = 2;

    public Subtract(Expression3 a, Expression3 b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return a.evaluate(x, y, z) - b.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " - " + (b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
