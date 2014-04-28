package calc.calclib.numsystems;

import calc.calclib.exceptions.CalcException;
import calc.calclib.exceptions.DivideByZeroException;
import calc.calclib.exceptions.IncorrectLogarithmArgument;
import com.sun.istack.internal.NotNull;

import java.math.BigInteger;

/**
 * @author volhovm
 *         Created on 21.04.14
 */

public class CalcInteger implements CalcNumerable<CalcInteger>, Comparable<CalcInteger>, Convertible {
    @NotNull
    private
    Integer a;

    public CalcInteger(Integer a) {
        this.a = a;
    }

    @Override
    public CalcInteger id() {
        return this;
    }

    @Override
    public CalcInteger plus(CalcInteger b) throws CalcException {
        long ret = (long) a + (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInteger((int) ret);
    }

    @Override
    public CalcInteger mul(CalcInteger b) {
        long ret = (long) a * (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInteger((int) ret);
    }

    @Override
    public CalcInteger div(CalcInteger b) {
        if (b.a == 0) throw new DivideByZeroException(a.toString());
        long ret = (long) a / (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInteger((int) ret);
    }

    @Override
    public CalcInteger sub(CalcInteger b) {
        long ret = (long) a - (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInteger((int) ret);
    }

    @Override
    public CalcInteger power(CalcInteger b) {
        if (b.a < 0) { //wow, such crutch
            int prevA = a;
            a = 1;
            return div(new CalcInteger(prevA).power(b.unaryMin()));
        }
        long ret = 1;
        for (int i = 0; i < b.a; i++) {
            ret = ret * a;
        }
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInteger((int) ret);
    }

    @Override
    public CalcInteger abs() {
        return new CalcInteger(Math.abs(a));
    }

    @Override
    public CalcInteger binaryLog() {
        if (a <= 0) {
            throw new IncorrectLogarithmArgument();
        }
        long ret = 31 - Integer.numberOfLeadingZeros(a);
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
//            throw new OverflowException(a.toString());
//        }
        return new CalcInteger((int) ret);
    }

    @Override
    public CalcInteger not() {
        return new CalcInteger(~a);
    }

    @Override
    public CalcInteger unaryMin() {
        return new CalcInteger(-a);
    }

    @Override
    public CalcInteger parse(String s) {
        return new CalcInteger(Integer.parseInt(s));
    }

    public String toString() {
        return a.toString();
    }

    @Override
    public int compareTo(@NotNull CalcInteger o) {
        if (o == null) throw new NullPointerException("comparing null objects");
        return a.compareTo(o.a);
    }

    /////////////////////////////////////////////

    @Override
    public CalcInteger changeArg(Number arg) {
        a = (Integer) arg;
        return this;
    }

    @Override
    public <Z extends Convertible> CalcInteger getInstance(Z a) {
        return new CalcInteger(a.toInteger());
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public CalcInteger replace(CalcNumerable a) {
        this.a = a.toInteger();
        return this;
    }

    @Override
    public Integer toInteger() {
        return a;
    }

    @Override
    public Double toDouble() {
        return a.doubleValue();
    }

    @Override
    public BigInteger toBigInt() {
        return new BigInteger(a.toString());
    }
}
