package calc.calclib.numsystems;

import calc.calclib.exceptions.CalcException;
import calc.calclib.exceptions.DivideByZeroException;
import calc.calclib.exceptions.IncorrectLogarithmArgument;
import com.sun.istack.internal.NotNull;

import java.math.BigInteger;

/**
 * @author volhovm
 * Created on 21.04.14
 */

public class CalcInt implements CalcNumerable<CalcInt>, Comparable<CalcInt>, Convertible {
    @NotNull
    private
    Integer a;

    public CalcInt(Integer a) {
        this.a = a;
    }

    @Override
    public CalcInt id() {
        return this;
    }

    @Override
    public CalcInt plus(CalcInt b) throws CalcException {
        long ret = (long) a + (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInt((int) ret);
    }

    @Override
    public CalcInt mul(CalcInt b) {
        long ret = (long) a * (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInt((int) ret);
    }

    @Override
    public CalcInt div(CalcInt b) {
        if (b.a == 0) throw new DivideByZeroException(a.toString());
        long ret = (long) a / (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInt((int) ret);
    }

    @Override
    public CalcInt sub(CalcInt b) {
        long ret = (long) a - (long) b.a;
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInt((int) ret);
    }

    @Override
    public CalcInt power(CalcInt b) {
        long ret = 1;
        for (int i = 0; i < b.a; i++) {
            ret = ret * a;
        }
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
//            throw new OverflowException(a.toString());
        return new CalcInt((int) ret);
    }

    @Override
    public CalcInt abs() {
        return new CalcInt(Math.abs(a));
    }

    @Override
    public CalcInt binaryLog() {
        if (a <= 0) {
            throw new IncorrectLogarithmArgument();
        }
        long ret = 31 - Integer.numberOfLeadingZeros(a);
//        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
//            throw new OverflowException(a.toString());
//        }
        return new CalcInt((int) ret);
    }

    @Override
    public CalcInt not() {
        return new CalcInt(~a);
    }

    @Override
    public CalcInt unaryMin() {
        return new CalcInt(-a);
    }

    @Override
    public CalcInt parse(String s) {
        return new CalcInt(Integer.parseInt(s));
    }

    public String toString() {
        return a.toString();
    }

    @Override
    public int compareTo(@NotNull CalcInt o) {
        if (o == null) throw new NullPointerException("comparing null objects");
        return a.compareTo(o.a);
    }

    /////////////////////////////////////////////

    @Override
    public CalcInt changeArg(Number arg) {
        a = (Integer) arg;
        return this;
    }

    @Override
    public <Z extends Convertible> CalcInt getInstance(Z a) {
        return new CalcInt(a.toInteger());
    }

    @Override
    public String getType() {
        return "Integer";
    }

    @Override
    public CalcInt replace(CalcNumerable a) {
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
