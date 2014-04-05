package main.calc.calclib;

/**
 * @author volhovm
 */

public abstract class UnaryOperations implements Expression {
    public Expression a;

    protected UnaryOperations(Expression a) {
        this.a = a;
    }

    @Override
    public abstract double evaluate(double x, double y, double z);
}
