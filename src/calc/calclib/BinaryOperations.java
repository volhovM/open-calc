package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public abstract class BinaryOperations<T extends CalcNumerable> implements Expression<T> {
    final Expression<T> a;
    final Expression<T> b;

    BinaryOperations(Expression<T> a, Expression<T> b) {
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

