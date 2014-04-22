package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class Divide<T extends CalcNumerable<T>> extends BinaryOperations<T> {
    private final short PRIORITY = 3;

    public Divide(Expression<T> a, Expression<T> b) {
        super(a, b);
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalcException {
        return a.evaluate(x, y, z).div(b.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " / " + (
                b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
