package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public final class Multiply<T extends CalcNumerable<T>> extends NaryOperation<T> {
    private final short PRIORITY = 3;

    @SafeVarargs
    public Multiply(Expression<T>... expressions) {
        super(expressions);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return arguments.stream()
                .map((Expression<T> a) -> a.evaluate(args))
                .reduce((a, b) -> a.mul(b))
                .get();
    }

    @Override
    protected String getJoiner() {
        return " * ";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    Expression<T> simplifyTwo(Expression<T> left, Expression<T> right, T type) {
        if (left instanceof Const && right instanceof Const)
            return new Const<T>(type.parse(((Const<T>) left).getConstant().mul(((Const<T>) right).getConstant()).toString()));
        else if (left.equals(right)) return new Power<T>(left, new Const<T>(type.parse("2")));
        else if (left instanceof Power && right instanceof Power && ((Power<T>) left).getA().equals(((Power<T>) right).getA())) {
            return new Power<T>(((Power<T>) left).getA(), new Add<T>(((Power<T>) left).getB(), ((Power<T>) right).getB()));
        } else if (
                left instanceof Power && !(right instanceof Power) &&
                        (((Power<T>) left).getB().equals(new UnaryMin<T>(new Const<T>(type.parse("1"))))
                                || ((Power<T>) left).getB().equals(new Const<T>(type.parse("-1"))) && ((Power<T>) left).getA().equals(right))

                        ||
                        right instanceof Power && !(left instanceof Power) &&
                                (((Power<T>) right).getB().equals(new UnaryMin<T>(new Const<T>(type.parse("1"))))
                                        || ((Power<T>) right).getB().equals(new Const<T>(type.parse("-1")))) && left.equals(((Power<T>) right).getA())) {
            return new Const<T>(type.parse("1"));
        }
        return null;
    }
}
