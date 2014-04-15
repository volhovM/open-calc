package main.calc.calclib;

import main.calc.calclib.exceptions.CalcException;
import main.calc.calclib.exceptions.IncorrectLogariphmArgument;
import main.calc.calclib.exceptions.OverflowException;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class BinaryLog extends UnaryOperations {
    final short PRIORITY = 4;
    //    final static double ln2 = 0.30102999566;

    public BinaryLog(Expression3 a) {
        super(a);
    }

    @Override
    public int evaluate(int x, int y, int z) throws CalcException {
        double arg = (double) a.evaluate(x, y, z);
        if (arg <= 0) {
            throw new IncorrectLogariphmArgument();
        }
        long ret = 31 - Integer.numberOfLeadingZeros((int) arg);
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            throw new OverflowException("there was an overflow while evaluating: " + this);
        }
        return (int) ret;
    }

    @Override
    public String toString() {
        return ("lb(" + a.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
