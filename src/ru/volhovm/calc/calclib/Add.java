package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public final class Add<T extends CalcNumerable<T>> extends NaryOperation<T> {
    private final short PRIORITY = 2;

    @SafeVarargs
    public Add(Expression<T>... expressions) {
        super(expressions);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return arguments.stream()
                .map((Expression<T> a) -> a.evaluate(args))
                .reduce((a, b) -> a.plus(b))
                .get();
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    Expression<T> simplifyTwo(Expression<T> left, Expression<T> right, T type) {
        if (left instanceof Const && right instanceof Const) return new Const<T>(evaluate());
        else if (left.equals(right)) return new Multiply<T>(new Const<T>(type.parse("2")), left);
        else if ((left instanceof UnaryMin && ((UnaryMin<T>) left).a.equals(right)) ||
                (right instanceof UnaryMin && ((UnaryMin<T>) right).a.equals(left))) {
            return new Const<T>(type.parse("0"));
        }
        return null;
    }

    @Override
    protected String getJoiner() {
        return " + ";
    }
}
