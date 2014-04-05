package main.calc.calclib;

/**
 * @author volhovm
 */

@Deprecated
public class Ternary implements Expression {
    Expression a, b, c;

    private Ternary() {}
    //    public Ternary(Expression a, Expression b, Expression c) {
    //        this.a = a;
//        this.b = b;
//        this.c = c;
//    }

    @Override
    public double evaluate(double x, double y, double z) {
        return a.evaluate(x, y, z) != 0 ? b.evaluate(x, y, z) : c.evaluate(x, y, z);
    }

    @Override
    public short getPriority() {
        return -1;
    }
}
