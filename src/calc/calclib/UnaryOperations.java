package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public abstract class UnaryOperations<T extends CalcNumerable<T>> implements Expression<T> {
    Expression<T> a;

    UnaryOperations(Expression<T> a) {
        this.a = a;
    }

    @Override
    public abstract T evaluate(T x, T y, T z) throws CalcException;
}
