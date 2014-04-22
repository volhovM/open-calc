package calc.calclib;

import calc.calclib.exceptions.IncorrectVariableException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class Variable<T extends CalcNumerable<T>> implements Expression<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 5;
    private final char variableType;

    public char getVariableType() {
        return variableType;
    }

    public Variable(String s) {
        variableType = s.charAt(0);
    }

    @Override
    public T evaluate(T x, T y, T z) throws IncorrectVariableException {
        switch (variableType) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
            default:
                throw new IncorrectVariableException(variableType);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(variableType);
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
