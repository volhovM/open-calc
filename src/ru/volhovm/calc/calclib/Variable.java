package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.IncorrectVariableException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public final class Variable<T extends CalcNumerable<T>> implements Expression<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 5;
    private final char variableType;

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
                if (args.length < 1 || args[0] == null) throw new IncorrectVariableException('x');
                return args[0];
            case 'y':
                if (args.length < 2 || args[1] == null) throw new IncorrectVariableException('y');
                return args[1];
            case 'z':
                if (args.length < 3 || args[2] == null) throw new IncorrectVariableException('z');
                return args[2];
            default:
                int num = variableType - 'a' + 3;
                if (args.length > num && args[num] != null) return args[num];
                else throw new IncorrectVariableException(variableType);
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

    @Override
    public Expression<T> simplify(T type) {
        return this;
    }

    @Override
    public boolean equals(Expression<T> a) {
        return a instanceof Variable && ((Variable) a).getVariableType() == getVariableType();
    }
}
