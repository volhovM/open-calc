package main.calc.calclib;

/**
 * @author volhovm
 */

public class Variable implements Expression3 {
    final short PRIORITY = 5;
    private char variableType;
    private int power; //TODO insert power into parser, insert methods into simplifier

    public char getVariableType() {
        return variableType;
    }

    public Variable(String s) {
        variableType = s.charAt(0);
        power = 1;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (variableType) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
            default:
                return 0;
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
