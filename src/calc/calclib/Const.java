package calc.calclib;

import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public final class Const<T extends CalcNumerable<T>> implements Expression<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 5;
    T constant; //FIXME mutable

    public T getConstant() {
        return constant;
    }

    public Const(T a) { //плохо масштабируется -- потом пофиксим
        constant = a;
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) {
        return constant;
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
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
    public boolean equals(Expression<T> a) {
        return a instanceof Const && ((Const) a).getConstant().equals(this.constant);
    }
}
