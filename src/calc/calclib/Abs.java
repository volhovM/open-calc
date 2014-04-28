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

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).abs();
    }

    @Override
    public String toString() {
        return "abs(" + a.toString() + ")";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify() {
        a = a.simplify();
        if (a instanceof Const) {
            ((Const) a).constant = ((Const) a).constant.abs();
            return a;
        }
        return this;
    }
}
