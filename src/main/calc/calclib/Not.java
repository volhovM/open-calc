package main.calc.calclib;

/**
 * @author volhovm
 */

@Deprecated
public class Not extends UnaryOperation implements Expression {
    final short PRIORITY = 4;

    public Not(Expression a) {
        super(a);
    }

    @Override
    public double evaluate(double x, double y, double z) {
        //        return Unsafe.getUnsafe().;
        return 0;
    }

    @Override
    public String toString() {
        return "~" + (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")");
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
