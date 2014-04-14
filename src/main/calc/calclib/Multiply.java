package main.calc.calclib;

import main.calc.calclib.Exceptions.CalcException;
import main.calc.calclib.Exceptions.OverflowException;

/**
 * @author volhovm
 */

public class Multiply extends BinaryOperations implements Expression3 {
    final short PRIORITY = 3;

    public Multiply(Expression3 a, Expression3 b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) throws CalcException {
        long ret = (long) a.evaluate(x, y, z) * (long) b.evaluate(x, y, z);
        if (ret > Integer.MAX_VALUE || ret <= Integer.MIN_VALUE) {
            throw new OverflowException("there was an overflow while evaluating: " + this);
        }
        return (int) ret;
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " * " + (
            b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
