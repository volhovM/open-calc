package main.calc.calclib;

/**
 * @author volhovm
 */

public abstract class UnaryOperation implements Expression {
    public Expression a;

    protected UnaryOperation(Expression a) {
        this.a = a;
    }

    @Override
    public abstract double evaluate(double x, double y, double z);
}
