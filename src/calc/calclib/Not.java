package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public final class Not<T extends CalcNumerable<T>> extends UnaryOperation<T> {
    private final short PRIORITY = 4;

    public Not(Expression<T> a) {
        super(a);
    }

    @Override
    protected String getJoiner() {
        return "~";
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).not();
    }


    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify(T type) {
        Expression<T> curr = a.simplify(type);
        if (curr instanceof Const) {
            ((Const) curr).constant = ((Const) curr).constant.not();
            return curr;
        }
        return this;
    }
}
