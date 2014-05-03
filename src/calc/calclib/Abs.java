package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public final class Abs<T extends CalcNumerable<T>> extends UnaryOperation<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 4;

    public Abs(Expression<T> a) {
        super(a);
    }

    @Override
    protected String getJoiner() {
        return "abs";
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).abs();
    }


    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify(T type) {
        Expression<T> curr = a.simplify(type);
        if (curr instanceof Const) {
            ((Const) curr).constant = ((Const) curr).constant.abs();
        }
        return curr;
    }
}
