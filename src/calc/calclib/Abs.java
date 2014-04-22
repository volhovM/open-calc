package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class Abs<T extends CalcNumerable<T>> extends UnaryOperations<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 4;

    public Abs(Expression<T> a) {
        super(a);
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalcException {
        return a.evaluate(x, y, z).abs();
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
