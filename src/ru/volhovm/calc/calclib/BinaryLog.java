package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public final class BinaryLog<T extends CalcNumerable<T>> extends UnaryOperation<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 4;

    public BinaryLog(Expression<T> a) {
        super(a);
    }

    @Override
    protected String getJoiner() {
        return "lb";
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).binaryLog();
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify(T type) {
        Expression<T> curr = a.simplify(type);
        if (curr instanceof Const) {
            ((Const) curr).constant = ((Const) curr).constant.binaryLog();
            return curr;
        }
        return curr;
    }
}
