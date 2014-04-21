package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.MyCalcCalculable;

/**
 * @author volhovm
 */

public abstract class BinaryOperations<T extends MyCalcCalculable> implements Expression3<T> {
    public Expression3<T> a, b;

    protected BinaryOperations(Expression3<T> a, Expression3<T> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    abstract public T evaluate(T x, T y, T z) throws CalcException;

    @Override
    abstract public String toString();

    //    @Override
    //    abstract public Expression3 simplify();
}

