package calc.calclib;

import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 *         Created on 30.04.14
 */
public abstract class BinaryOperation<T extends CalcNumerable<T>> implements Expression<T> {
    final Expression<T> a;
    final Expression<T> b;

    public Expression<T> getA() {
        return a;
    }

    public Expression<T> getB() {
        return b;
    }

    public BinaryOperation(Expression<T> a, Expression<T> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public short getPriority() {
        return -1;
    }

    abstract String getJoiner();

    @Override
    public String toString() {
        return ((a.getPriority() >= getPriority()) ? a.toString() : ("(" + a.toString() + ")"))
                + getJoiner()
                + (b.getPriority() >= getPriority() ? b.toString() : ("(" + b.toString() + ")"));
    }

    @Override
    public boolean equals(Expression<T> a) {
        if (this.getClass() == a.getClass()) {
            return (this.a.equals(((BinaryOperation<T>) a).getA()) && this.b.equals(((BinaryOperation<T>) a).getB()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }
}
