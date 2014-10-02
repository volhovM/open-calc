package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */
public final class Power<T extends CalcNumerable<T>> extends BinaryOperation<T> {
    private static final short PRIORITY = 5;
    private boolean isBOne = false;

    public Power(Expression<T> a, Expression<T> b) {
        super(a, b);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).power(b.evaluate(args));
    }

    @Override
    public String toString() {
        if (isBOne) return a.toString();
        else return super.toString();
    }

    @Override
    String getJoiner() {
        return "^";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify(T type) {
        Expression<T> ret = new Power<T>(a.simplify(type), b.simplify(type));
        if (b instanceof Const) {
            if (b.equals(new Const<T>(type.parse("1")))) {
                isBOne = true;
                ret = a;
            } else if (b.equals(new Const<T>(type.parse("0")))) {
                ret = new Const<T>(type.parse("0"));
            } else if (a instanceof Const) {
                return new Const<T>(evaluate());
            }
        }
        return ret;
    }
}
