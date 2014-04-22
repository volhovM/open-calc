package calc.calclib;

import calc.calclib.exceptions.IncorrectVariableException;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class Variable<T extends CalcNumerable<T>> implements Expression3<T> {
    final short PRIORITY = 5;
    private char variableType;

    public char getVariableType() {
        return variableType;
    }

    public Variable(char a) {
        variableType = a;
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) { //x, y, z, a...w
        switch (variableType) {
            case 'x':
                return args[0];
            case 'y':
                return args[1];
            case 'z':
                return args[2];
            default:
                int num = variableType - 'a' + 3;
                if (args.length > num) return args[num];
                else throw new IncorrectVariableException();
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
