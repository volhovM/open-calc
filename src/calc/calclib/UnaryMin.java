package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class UnaryMin<T extends CalcNumerable<T>> extends UnaryOperations<T> {
    private final short PRIORITY = 4;

    public UnaryMin(Expression3<T> a) {
        super(a);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).unaryMin();
    }

    @Override
    public String toString() {
        return "-" + (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
