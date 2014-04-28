package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author volhovm
 */

public class Add<T extends CalcNumerable<T>> extends BinaryOperations<T> {
    private final short PRIORITY = 2;

    @SafeVarargs
    public Add(Expression<T>... expressions) {
        super(expressions);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return arguments.stream()
                .map((Expression<T> a) -> a.evaluate(args))
                .reduce((a, b) -> a.plus(b))
                .get();
    }

    @Override
    public String toString() {
        return arguments.stream()
                .map((Expression<T> x) -> x.getPriority() >= PRIORITY ? x.toString() :
                        "(" + x.toString() + ")")
                .collect(Collectors.joining(" + "));
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify() {
        for (int i = 0; i < arguments.size(); i++) {
            arguments.set(i, arguments.get(i).simplify());
        }
        for (int i = 0; i < arguments.size(); i++) {
            for (int j = i; j < arguments.size(); j++) {
                if (arguments.size() > 1) {
                    Expression<T> x = arguments.get(i);
                    Expression<T> y = arguments.get(j);

                }
            }
        }
    }
}
