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

public abstract class BinaryOperations<T extends CalcNumerable> implements Expression3<T> {
    protected ArrayList<Expression3<T>> arguments;

    @SafeVarargs
    protected BinaryOperations(@NotNull Expression3<T>... expressions) {
        arguments = new ArrayList<>();
        Collections.addAll(arguments, expressions);
    }

    public Expression3<T> getOnlyArgument() {
        assert size() == 1;
        return arguments.get(0);
    }

    public void addArgument(@NotNull Expression3<T> expression) {
        if (expression == null) throw new NullPointerException();
        arguments.add(expression);
    }

    public Expression3<T> getArgument(int index) {
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


    public Stream<Expression3<T>> stream() {
        return arguments.stream();
    }
}

