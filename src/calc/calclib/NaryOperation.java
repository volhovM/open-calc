package calc.calclib;

import calc.calclib.numsystems.CalcNumerable;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author volhovm
 */

public abstract class NaryOperation<T extends CalcNumerable> implements Expression<T> {
    final private short PRIORITY = -1;
    final ArrayList<Expression<T>> arguments;

    @SafeVarargs
    public NaryOperation(@NotNull Expression<T>... expressions) {
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

    @Override
    public Expression<T> simplify(T type) {
        for (int i = 0; i < arguments.size(); i++) { //simplify all the arguments
            arguments.set(i, arguments.get(i).simplify(type));
        }
        for (int i = 0; i < arguments.size(); i++) {
            for (int j = i + 1; j < arguments.size(); j++) {
                if (arguments.size() > 1) {
                    Expression<T> left = arguments.get(i);
                    Expression<T> right = arguments.get(j);
                    Expression<T> res = simplifyTwo(left, right, type);
                    if (res != null) {
                        arguments.remove(left);
                        arguments.remove(right);
                        arguments.add(res);
                    }
                } else return this;
            }
        }
        return this;
    }

    abstract Expression<T> simplifyTwo(Expression<T> left, Expression<T> right, T type);

    @Override
    public boolean equals(Expression<T> a) {
        return a instanceof NaryOperation
                && new HashSet<>(this.arguments).equals(new HashSet<>(((NaryOperation<T>) a).arguments));
    }

    //TODO override hash

    @Override
    public String toString() {
        return arguments.stream()
                .map((Expression<T> x) -> x.getPriority() >= PRIORITY ? x.toString() :
                        "(" + x.toString() + ")")
                .collect(Collectors.joining(getJoiner()));
    }

    abstract String getJoiner();

    public Stream<Expression<T>> stream() {
        return arguments.stream();
    }

    public int size() {
        return arguments.size();
    }
}

