package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.exceptions.FunctionNotDefined;
import calc.calclib.numsystems.CalcDouble;
import calc.calclib.numsystems.CalcInt;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 *         Created on 22.04.14
 */
//to add function add it to enum, then define it in calcNumerable and all implementations
public class AbstractFunction<T extends CalcNumerable<T>> implements Expression<T> {
    private interface Applyable {
        public <T extends CalcNumerable<T>> T apply(T arg);
    }

    public enum Functions implements Applyable {
        sin {
            public <T extends CalcNumerable<T>> T apply(T arg) {
                CalcNumerable z = arg.id();
                if (z instanceof CalcInt) {
                    return arg.getInstance(0);
                } else if (z instanceof CalcDouble) {
                    return arg.getInstance(Math.sin(arg.getInnerVariable()));
                }
                return arg.sin();
            }
        },
        factorial {
            public <T extends CalcNumerable<T>> T apply(T arg) {
                return arg.factorial();
            }
        }
    }

    @SuppressWarnings("FieldCanBeLocal")
    private final short PRIORITY = 4;

    Functions functionType;
    final Expression<T> a;

    public AbstractFunction(String s, Expression<T> a) {
        if (Functions.valueOf(s) != null) {
            functionType = Functions.valueOf(s);
            this.a = a;
            int b = 5;
        } else throw new FunctionNotDefined(s + " is not defined properly");
    }

    @Override
    public T evaluate(T x, T y, T z) throws CalcException {
        return functionType.apply(a.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return functionType.toString() + "(" + a.toString() + ")";
    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
