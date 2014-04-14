package main.calc.calclib;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @author volhovm
 */

public abstract class BinaryOperation implements Expression {
    protected ArrayList<Expression> arguments;

    protected BinaryOperation(@NotNull Expression... expressions) {
        arguments = new ArrayList<>();
        Collections.addAll(arguments, expressions);
    }

    public void addArgument(@NotNull Expression expression) {
        if (arguments != null) {
            arguments.add(expression);
        }
    }

    public Expression getOnlyOperation() {
        if (size() == 1) {
            return arguments.get(0);
        } else {
            return null;
        }
    }

    public Expression getOperation(int index) {
        return arguments.get(index);
    }

    public void removeOperation(int index) {
        arguments.remove(index);
    }

    @Override
    abstract public double evaluate(double x, double y, double z);

    @Override
    abstract public String toString();

    public int size() {
        return arguments.size();
    }

    public Stream<Expression> stream() {
        return arguments.stream();
    }
    //    @Override
    //    abstract public Expression simplify();
}

