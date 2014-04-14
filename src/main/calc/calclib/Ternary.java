package main.calc.calclib;

import main.calc.calclib.exceptions.CalcException;

/**
 * @author volhovm
 */

@SuppressWarnings("UnusedDeclaration")
@Deprecated
public class Ternary implements Expression3 {
    Expression3 a, b, c;

    private Ternary() {}
    //    public Ternary(Expression3 a, Expression3 b, Expression3 c) {
    //        this.a = a;
    //        this.b = b;
    //        this.c = c;
    //    }

    @Override
    public int evaluate(int x, int y, int z) throws CalcException {
        return a.evaluate(x, y, z) != 0 ? b.evaluate(x, y, z) : c.evaluate(x, y, z);
    }

    @Override
    public short getPriority() {
        return -1;
    }
}
