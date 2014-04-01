package main.calc.calclib;

/**
 * Created by volhovm on 3/15/14.
 */


public class Const implements Expression3 {
    final short PRIORITY = 5;
    private final int constant;

    public int getConstant() {
        return constant;
    }

    public Const(int a) {
        constant = a;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return constant;
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
