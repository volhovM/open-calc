package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @author volhovm
 */

public abstract class BinaryOperations<T extends CalcNumerable> implements Expression<T> {
    protected ArrayList<Expression<T>> arguments;

    @SafeVarargs
    protected BinaryOperations(@NotNull Expression<T>... expressions) {
        arguments = new ArrayList<>();
        Collections.addAll(arguments, expressions);
    }

    public Expression<T> getOnlyArgument() {
        assert size() == 1;
        return arguments.get(0);
    }

    public void addArgument(@NotNull Expression<T> expression) {
        if (expression == null) throw new NullPointerException();
        arguments.add(expression);
    }

    public Expression<T> getArgument(int index) {
        return arguments.get(index);
    }

    public void removeArgument(int index) {
        arguments.remove(index);
    }

    @Override
    abstract public T evaluate(T... args) throws CalcException;

    @Override
    abstract public String toString();

    public int size() {
        return arguments.size();
    }


    public Stream<Expression<T>> stream() {
        return arguments.stream();
    }
}

