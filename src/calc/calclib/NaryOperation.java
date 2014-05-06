package calc.calclib;

import calc.calclib.numsystems.CalcNumerable;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author volhovm
 */

public abstract class NaryOperation<T extends CalcNumerable> implements Expression<T> {
    final private short PRIORITY = -1;
    final ArrayList<Expression<T>> arguments;
    private final int SIMPLIFY_RATE = 1;

    @SafeVarargs
    public NaryOperation(@NotNull Expression<T>... expressions) {
        arguments = new ArrayList<Expression<T>>();
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
        for (int k = 0; k < SIMPLIFY_RATE; k++) {
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
        }
        return this;
    }

    abstract Expression<T> simplifyTwo(Expression<T> left, Expression<T> right, T type);

    @Override
    public boolean equals(Expression<T> a) {
        if (a instanceof NaryOperation) {
            if (size() != ((NaryOperation) a).size()) return false;
            c1:
            for (int i = 0; i < size(); i++) {
                for (int j = i; j < ((NaryOperation) a).size(); j++) {
                    Expression<T> iArg = arguments.get(i);
                    Expression<T> jArg = arguments.get(j);
                    if (jArg.equals(iArg)) continue c1;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return arguments.stream()
                .map((Expression<T> x) -> x.getPriority() > getPriority() ? x.toString() :
                        "(" + x.toString() + ")")
                .collect(Collectors.joining(getJoiner()));
    }

    abstract String getJoiner();

    public Stream<Expression<T>> stream() {
        return arguments.stream();
    }

    @Override
    public int hashCode() {
        int result = (int) PRIORITY;
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        return result;
    }

    public int size() {
        return arguments.size();
    }
}

