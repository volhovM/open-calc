package main.calc.calclib;

/**
 * Created by volhovm on 3/15/14.
 */
public class Divide extends BinaryOperations implements Expression3 {
    final short PRIORITY = 3;

    public Divide(Expression3 a, Expression3 b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return a.evaluate(x, y, z) / b.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " / " + (b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
