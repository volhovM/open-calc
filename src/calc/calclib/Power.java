package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;

import java.util.stream.Collectors;

/**
 * @author volhovm
 */
public class Power<T extends CalcNumerable<T>> extends BinaryOperations<T> {
    private static final short PRIORITY = 5;

    @SafeVarargs
    public Power(Expression<T>... expressions) {
        super(expressions);
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return arguments.stream()
                .map((Expression<T> a) -> a.evaluate(args))
                .reduce((a, b) -> a.power(b))
                .get();
    }

    @Override
    public String toString() {
        return arguments.stream()
                .map((Expression<T> x) -> x.getPriority() >= PRIORITY ? x.toString() :
                        "(" + x.toString() + ")")
                .collect(Collectors.joining(" ^ "));
    }

    private String getOP() {
        return " ^ ";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
