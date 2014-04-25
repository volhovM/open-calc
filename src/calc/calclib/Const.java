package calc.calclib;

import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class Const<T extends CalcNumerable<T>> implements Expression<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 5;
    private final T constant;

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
}
