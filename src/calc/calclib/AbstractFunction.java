package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.exceptions.FunctionNotDefined;
import calc.calclib.numsystems.CalcNumerable;

import java.math.BigInteger;
import java.util.function.Function;

/**
 * @author volhovm
 *         Created on 22.04.14
 */
public final class AbstractFunction<T extends CalcNumerable<T>> implements Expression<T> {
    private static enum Functions implements Applyable {
        //Put your function here:
        sin {
            @Override
            public void init() {
                Implementation.DoubleImp.setFunction(a -> a.changeArg(Math.sin(a.toDouble())));
            }
        },
        factorial {
            @Override
            public void init() {
                Implementation.IntegerImp.setFunction(a -> {
                    int ret = 1;
                    int b = a.toInteger();
                    for (int i = 1; i <= b; i++) {
                        ret *= i;
                    }
                    return a.changeArg(ret);
                });
                Implementation.DoubleImp.setFunction(a -> {
                    int ret = 1;
                    int b = a.toInteger();
                    for (int i = 1; i <= b; i++) {
                        ret *= i;
                    }
                    return a.changeArg(ret);
                });
                Implementation.BigIntegerImp.setFunction(a -> {
                    BigInteger ret = BigInteger.ONE;
                    BigInteger b = a.toBigInt();
                    for (int i = 1; b.compareTo(new BigInteger(String.valueOf(i))) <= 0; i++) {
                        ret = ret.multiply(new BigInteger(String.valueOf(i)));
                    }
                    return a.changeArg(ret);
                });
            }
        };

        public abstract void init();

        public <T extends CalcNumerable<T>> T apply(T arg) {
            return arg.replace(
                    Implementation.valueOf(
                            arg.getType() + "Imp")
                            .currentImpFoo.apply(arg)
            );
        }

        public <T extends CalcNumerable<T>> boolean isDefined(T arg) {
            return Implementation.valueOf(
                    arg.getType() + "Imp")
                    .currentImpFoo.apply(arg) != null;
        }

        private enum Implementation {
            DoubleImp, IntegerImp, BigIntegerImp;

            public Function<CalcNumerable, CalcNumerable> currentImpFoo = a -> null;

            public void setFunction(Function<CalcNumerable, CalcNumerable> currentImpFoo) {
                this.currentImpFoo = currentImpFoo;
            }
        }
    }

    private interface Applyable {
        public <T extends CalcNumerable<T>> T apply(T arg);
    }

    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 4;

    private final Functions functionType;
    private final Expression<T> a;

    public AbstractFunction(String s, T type, Expression<T> a) {
        try {
            functionType = Functions.valueOf(s);
        } catch (IllegalArgumentException exc) {
            throw new FunctionNotDefined("'" + s + "' is not defined properly");
        }
        functionType.init();
        if (!functionType.isDefined(type))
            throw new FunctionNotDefined("'" + s + "' is not defined for type '" + type.getType() + "'");
        this.a = a;
    }

    @SafeVarargs
    @Override
    public final T evaluate(T... args) throws CalcException {
        return functionType.apply(a.evaluate(args));
    }

    @Override
    public String toString() {
        return functionType.toString() + "(" + a.toString() + ")";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }

    @Override
    public Expression<T> simplify(T type) {
        Expression<T> curr = a.simplify(type);
        if (curr instanceof Const) {
            evaluate();
        }
        return curr;
    }

    @Override
    public boolean equals(Expression<T> a) {
        return a instanceof AbstractFunction && this.a.equals(a)
                && this.functionType.equals(((AbstractFunction) a).functionType);
    }
}