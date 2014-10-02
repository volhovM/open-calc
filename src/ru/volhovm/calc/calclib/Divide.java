package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

@SuppressWarnings("UnusedDeclaration")
@Deprecated
public final class Divide<T extends CalcNumerable<T>> extends BinaryOperation<T> {
    private final short PRIORITY = 3;

    public Divide(Expression<T> a, Expression<T> b) {
        super(a, b);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).div(b.evaluate(args));
    }

    @Override
    String getJoiner() {
        return " / ";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify(T type) {
        return this; //i'd better not write that
    }

    //not defined for that
}
