package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.MyCalcCalculable;

/**
 * @author volhovm
 */

public class Add<T extends MyCalcCalculable<T>> extends BinaryOperations<T> {
    final short PRIORITY = 2;

    public Add(Expression3<T> a, Expression3<T> b) {
        super(a, b);
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalcException {
        T ret = a.evaluate(x, y, z);
        T s = ret.plus(b.evaluate(x, y, z));
//        if (ret > Integer.MAX_VALUE || ret <= Integer.MIN_VALUE) {
//            throw new OverflowException("there was an overflow while evaluating: " + this);
//        }
        return ret;
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " + " + (
            b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
