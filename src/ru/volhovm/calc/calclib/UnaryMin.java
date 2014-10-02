package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public final class UnaryMin<T extends CalcNumerable<T>> extends UnaryOperation<T> {
    private final short PRIORITY = 4;

    public UnaryMin(Expression<T> a) {
        super(a);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).unaryMin();
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    String getJoiner() {
        return "-";
    }

    @Override
    public Expression<T> simplify(T type) {
        Expression<T> ret = a.simplify(type);
        if (ret instanceof Const) {
            ((Const) ret).constant = ((Const) ret).constant.unaryMin();
            return ret;
        }
        return this;
    }
}
