package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class BinaryLog<T extends CalcNumerable<T>> extends UnaryOperations<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 4;

    public BinaryLog(Expression<T> a) {
        super(a);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return a.evaluate(args).binaryLog();
    }

    @Override
    public String toString() {
        return ("lb(" + a.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify() {
        a = a.simplify();
        if (a instanceof Const) {
            ((Const) a).constant = ((Const) a).constant.binaryLog();
            return a;
        }
        return this;
    }
}
