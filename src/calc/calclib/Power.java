package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.exceptions.OverflowException;

/**
 * @author volhovm
 */
public class Power extends BinaryOperations {
    private static final short PRIORITY = 5;

    public Power(Expression3 a, Expression3 b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) throws CalcException {
        long ret = (long) Math.pow(a.evaluate(x, y, z), b.evaluate(x, y, z));
        if (ret > Integer.MAX_VALUE) {
            throw new OverflowException("there was an overflow while evaluating: " + this);
        }
        return (int) ret;
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + getOP() + (
            b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    private String getOP() {return " ^ ";}

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
