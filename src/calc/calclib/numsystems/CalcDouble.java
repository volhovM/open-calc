package calc.calclib.numsystems;

import calc.calclib.exceptions.CalcException;
import calc.calclib.exceptions.DivideByZeroException;
import calc.calclib.exceptions.IncorrectLogarithmArgument;
import calc.calclib.exceptions.OverflowException;
import com.sun.istack.internal.NotNull;


/**
 * @author ${USERNAME}
 *         Created on 21.04.14
 */

public class CalcDouble implements CalcNumerable<CalcDouble>, Comparable<CalcDouble> {
    private
    @NotNull
    final
    Double a;

    private CalcDouble(Double a) {
        this.a = a;
    }

    @Override
    public CalcDouble plus(CalcDouble b) throws CalcException {
        Double ret = a + b.a;
        if (ret.isInfinite()) {
            throw new OverflowException(a.toString());
        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble mul(CalcDouble b) {
        Double ret = a * b.a;
        if (ret.isInfinite()) {
            throw new OverflowException(a.toString());
        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble div(CalcDouble b) {
        if (b.a == 0) throw new DivideByZeroException(a.toString());
        Double ret = a / b.a;
        if (ret.isInfinite()) {
            throw new OverflowException(a.toString());
        }
        if (ret.isNaN()) {
            throw new DivideByZeroException(a.toString());
        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble sub(CalcDouble b) {
        Double ret = a - b.a;
        if (ret.isInfinite()) {
            throw new OverflowException(a.toString());
        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble power(CalcDouble b) {
        Double ret = Math.pow(a, b.a);
        if (ret.isInfinite()) {
            throw new OverflowException(a.toString());
        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble abs() {
        return new CalcDouble(Math.abs(a));
    }

    @Override
    public CalcDouble binaryLog() {
        if (a <= 0) {
            throw new IncorrectLogarithmArgument();
        }
        Double ret = Math.log(a) / 0.693147d;
        if (ret.isInfinite()) {
            throw new OverflowException(a.toString());
        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble not() { //bitwise not is not supported in double
        return new CalcDouble(a);
    }

    @Override
    public CalcDouble unaryMin() {
        return new CalcDouble(-a);
    }

    @Override
    public CalcDouble parse(String s) {
        return new CalcDouble(Double.parseDouble(s));
    }

    public String toString() {
        return a.toString();
    }

    @Override
    public int compareTo(@NotNull CalcDouble o) {
        if (o == null) throw new NullPointerException("comparing null objects");
        return a.compareTo(o.a);
    }
}
