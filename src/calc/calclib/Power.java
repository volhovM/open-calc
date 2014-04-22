package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */
public class Power<T extends CalcNumerable<T>> extends BinaryOperations<T> {
    private static final short PRIORITY = 5;

    public Power(Expression<T> a, Expression<T> b) {
        super(a, b);
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalcException {
        return a.evaluate(x, y, z).power(b.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + getOP() + (
                b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    private String getOP() {
        return " ^ ";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
