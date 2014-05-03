package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

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
        if (left instanceof Const && right instanceof Const) return new Const<T>(evaluate());
        else if (left.equals(right)) return new Power<T>(left, new Const<T>(type.parse("2")));
        else if (left instanceof Power && right instanceof Power && ((Power<T>) left).getA().equals(((Power<T>) right).getA())) {
            return new Power<T>(((Power<T>) left).getA(), new Add<T>(((Power<T>) left).getB(), ((Power<T>) right).getB()));
        }
        return null;
    }
}
