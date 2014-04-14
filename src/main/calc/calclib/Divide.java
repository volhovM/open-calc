package main.calc.calclib;

import main.calc.calclib.Exceptions.CalcException;
import main.calc.calclib.Exceptions.DivideByZeroException;

/**
 * @author volhovm
 */

public class Divide extends BinaryOperations implements Expression3 {
    final short PRIORITY = 3;

    public Divide(Expression3 a, Expression3 b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) throws CalcException {
        int right = b.evaluate(x, y, z);
        if (right == 0) {
            throw new DivideByZeroException("Division by zero when evaluating" + this);
        }
        return a.evaluate(x, y, z) / right;
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " / " + (
            b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
