package main.calc.calclib;

/**
 * Created by volhovm on 02.04.14.
 */
public class Power extends BinaryOperations {
    final short PRIORITY = 4;

    public Power(Expression3 a, Expression3 b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) Math.pow(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " ^ " + (b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
