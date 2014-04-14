package main.calc.calclib;

import main.calc.calclib.exceptions.IncorrectVariableException;

/**
 * @author volhovm
 */

public class Variable implements Expression3 {
    final short PRIORITY = 5;
    private char variableType;

    public char getVariableType() {
        return variableType;
    }

    public Variable(String s) {
        variableType = s.charAt(0);
    }

    @Override
    public int evaluate(int x, int y, int z) throws IncorrectVariableException {
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
