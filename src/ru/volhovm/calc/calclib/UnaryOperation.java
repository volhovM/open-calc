package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public abstract class UnaryOperation<T extends CalcNumerable<T>> implements Expression<T> {
    final Expression<T> a;

    public Expression<T> getA() {
        return a;
    }

    public UnaryOperation(Expression<T> a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return getJoiner() + (a.getPriority() >= getPriority() ? a.toString() : "(" + a.toString() + ")");
    }

    abstract String getJoiner();

    @Override
    public boolean equals(Expression<T> a) {
        return (a.getClass() == this.getClass() && this.a.equals(a));
    }

    @Override
    public int hashCode() {
        return a != null ? a.hashCode() : 0;
    }
}
