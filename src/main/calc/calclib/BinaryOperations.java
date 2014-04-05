package main.calc.calclib;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author volhovm
 */

public abstract class BinaryOperations implements Expression {
    protected ArrayList<Expression> arguments;

    protected BinaryOperations(Expression... expressions) {
        arguments = new ArrayList<Expression>();
        Collections.addAll(arguments, expressions);
    }

    public void addArgument(Expression expression) {
        if (arguments != null) {
            arguments.add(expression);
        }
    }

    @Override
    abstract public double evaluate(double x, double y, double z);

    @Override
    abstract public String toString();

    //    @Override
    //    abstract public Expression simplify();
}

