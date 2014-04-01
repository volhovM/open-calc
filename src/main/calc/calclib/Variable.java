package main.calc.calclib;

/**
 * Created by volhovm on 3/15/14.
 */
public class Variable implements Expression3 {
    final short PRIORITY = 5;
    private char variableType;
    private int multiplier; //TODO insert power into parser, insert methods into simplifier
    private int power;

    public char getVariableType() {
        return variableType;
    }

    public Variable(String s) {
        variableType = s.charAt(0);
        multiplier = 1;
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
