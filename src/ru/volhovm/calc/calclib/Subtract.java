package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */
@Deprecated
public final class Subtract<T extends CalcNumerable<T>> extends BinaryOperation<T> {
    private final static short PRIORITY = 2;

    public Subtract(Expression<T> a, Expression<T> b) {
        super(a, b);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).sub(b.evaluate(args));
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify(T type) {
        return this;
    }

    @Override
    String getJoiner() {
        return " - ";
    }
}
